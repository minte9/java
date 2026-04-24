package com.example.fx.transfer;

import com.example.fx.account.Account;
import com.example.fx.account.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;

@Service
public class FxTransferService {
    private final AccountRepository accountRepository;
    private final FxTransferRepository transferRepository;

    public FxTransferService(AccountRepository accountRepository, FxTransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public FxTransferController.FxTransferView exchange(
            String idempotencyKey,
            FxTransferController.FxTransferRequest request
    ) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("Idempotency-Key header is required");
        }
        if (request.fromAccountId().equals(request.toAccountId())) {
            throw new IllegalArgumentException("Cannot exchange within the same account");
        }

        // Industry standard idea: retry-safe operations need an idempotency key.
        // If the client retries the same request, we return the original transfer.
        var existing = transferRepository.findByIdempotencyKey(idempotencyKey);
        if (existing.isPresent()) {
            return toView(existing.get());
        }

        Long firstId = Math.min(request.fromAccountId(), request.toAccountId());
        Long secondId = Math.max(request.fromAccountId(), request.toAccountId());

        // Both rows are locked by PostgreSQL until commit.
        // Ordering locks by ID reduces DB deadlock risk.
        Account first = accountRepository.findByIdForUpdate(firstId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + firstId));
        Account second = accountRepository.findByIdForUpdate(secondId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + secondId));

        Account from = request.fromAccountId().equals(first.getId()) ? first : second;
        Account to = request.toAccountId().equals(first.getId()) ? first : second;

        if (from.getCurrency().equals(to.getCurrency())) {
            throw new IllegalArgumentException("Use FX endpoint only for different currencies");
        }

        long targetAmountMinor = request.exchangeRate()
                .multiply(java.math.BigDecimal.valueOf(request.sourceAmountMinor()))
                .setScale(0, RoundingMode.HALF_UP)
                .longValueExact();

        from.withdraw(request.sourceAmountMinor());
        to.deposit(targetAmountMinor);

        FxTransfer transfer = new FxTransfer(
                idempotencyKey,
                from.getId(),
                to.getId(),
                request.sourceAmountMinor(),
                targetAmountMinor,
                request.exchangeRate()
        );

        return toView(transferRepository.save(transfer));
    }

    private FxTransferController.FxTransferView toView(FxTransfer t) {
        return new FxTransferController.FxTransferView(
                t.getId(), t.getIdempotencyKey(), t.getFromAccountId(), t.getToAccountId(),
                t.getSourceAmountMinor(), t.getTargetAmountMinor(), t.getExchangeRate()
        );
    }
}
