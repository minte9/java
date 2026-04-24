package com.example.fx;

import com.example.fx.account.*;
import com.example.fx.transfer.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FxTransferServiceTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired AccountRepository accountRepository;
    @Autowired FxTransferService transferService;

    @Test
    void exchangeMovesMoneyAtomically() {
        accountRepository.deleteAll();
        accountRepository.save(new Account(1L, CurrencyCode.EUR, 100_000));
        accountRepository.save(new Account(2L, CurrencyCode.USD, 100_000));

        transferService.exchange(UUID.randomUUID().toString(),
                new FxTransferController.FxTransferRequest(1L, 2L, 10_000, new BigDecimal("1.10")));

        Account eur = accountRepository.findById(1L).orElseThrow();
        Account usd = accountRepository.findById(2L).orElseThrow();

        assertThat(eur.getBalanceMinor()).isEqualTo(90_000);
        assertThat(usd.getBalanceMinor()).isEqualTo(111_000);
    }

    @Test
    void sameIdempotencyKeyDoesNotDoubleCharge() {
        accountRepository.deleteAll();
        accountRepository.save(new Account(1L, CurrencyCode.EUR, 100_000));
        accountRepository.save(new Account(2L, CurrencyCode.USD, 100_000));

        String key = UUID.randomUUID().toString();
        var request = new FxTransferController.FxTransferRequest(1L, 2L, 10_000, new BigDecimal("1.10"));

        transferService.exchange(key, request);
        transferService.exchange(key, request);

        Account eur = accountRepository.findById(1L).orElseThrow();
        Account usd = accountRepository.findById(2L).orElseThrow();

        assertThat(eur.getBalanceMinor()).isEqualTo(90_000);
        assertThat(usd.getBalanceMinor()).isEqualTo(111_000);
    }

    @Test
    void concurrentExchangesPreserveTotalPerCurrencyPairMath() throws Exception {
        accountRepository.deleteAll();
        accountRepository.save(new Account(1L, CurrencyCode.EUR, 100_000));
        accountRepository.save(new Account(2L, CurrencyCode.USD, 100_000));

        int threads = 10;
        int transfers = 50;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        CountDownLatch start = new CountDownLatch(1);

        for (int i = 0; i < transfers; i++) {
            executor.submit(() -> {
                try {
                    start.await();
                    transferService.exchange(UUID.randomUUID().toString(),
                            new FxTransferController.FxTransferRequest(1L, 2L, 100, new BigDecimal("1.00")));
                } catch (Exception ignored) {}
            });
        }

        start.countDown();
        executor.shutdown();
        assertThat(executor.awaitTermination(30, TimeUnit.SECONDS)).isTrue();

        Account eur = accountRepository.findById(1L).orElseThrow();
        Account usd = accountRepository.findById(2L).orElseThrow();

        assertThat(eur.getBalanceMinor()).isEqualTo(100_000 - transfers * 100L);
        assertThat(usd.getBalanceMinor()).isEqualTo(100_000 + transfers * 100L);
    }
}
