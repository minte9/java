package com.example.fx.transfer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transfers")
public class FxTransferController {
    private final FxTransferService transferService;

    public FxTransferController(FxTransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/fx")
    public FxTransferView transfer(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @Valid @RequestBody FxTransferRequest request
    ) {
        return transferService.exchange(idempotencyKey, request);
    }

    public record FxTransferRequest(
            @NotNull Long fromAccountId,
            @NotNull Long toAccountId,
            @Positive long sourceAmountMinor,
            @NotNull @DecimalMin(value = "0.00000001") BigDecimal exchangeRate
    ) {}

    public record FxTransferView(
            Long id,
            String idempotencyKey,
            Long fromAccountId,
            Long toAccountId,
            long sourceAmountMinor,
            long targetAmountMinor,
            BigDecimal exchangeRate
    ) {}
}
