package com.example.fx.config;

import com.example.fx.account.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seed(AccountRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Account(1L, CurrencyCode.EUR, 100_000));
                repository.save(new Account(2L, CurrencyCode.USD, 100_000));
                repository.save(new Account(3L, CurrencyCode.RON, 500_000));
            }
        };
    }
}
