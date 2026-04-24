package com.example.fx.transfer;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "fx_transfers", uniqueConstraints = @UniqueConstraint(columnNames = "idempotencyKey"))
public class FxTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String idempotencyKey;

    @Column(nullable = false)
    private Long fromAccountId;

    @Column(nullable = false)
    private Long toAccountId;

    @Column(nullable = false)
    private long sourceAmountMinor;

    @Column(nullable = false)
    private long targetAmountMinor;

    @Column(nullable = false, precision = 19, scale = 8)
    private BigDecimal exchangeRate;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    protected FxTransfer() {}

    public FxTransfer(String idempotencyKey, Long fromAccountId, Long toAccountId,
                      long sourceAmountMinor, long targetAmountMinor, BigDecimal exchangeRate) {
        this.idempotencyKey = idempotencyKey;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.sourceAmountMinor = sourceAmountMinor;
        this.targetAmountMinor = targetAmountMinor;
        this.exchangeRate = exchangeRate;
    }

    public Long getId() { return id; }
    public String getIdempotencyKey() { return idempotencyKey; }
    public Long getFromAccountId() { return fromAccountId; }
    public Long getToAccountId() { return toAccountId; }
    public long getSourceAmountMinor() { return sourceAmountMinor; }
    public long getTargetAmountMinor() { return targetAmountMinor; }
    public BigDecimal getExchangeRate() { return exchangeRate; }
    public Instant getCreatedAt() { return createdAt; }
}
