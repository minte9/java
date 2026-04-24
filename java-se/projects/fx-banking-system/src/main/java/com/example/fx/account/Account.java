package com.example.fx.account;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 3)
    private CurrencyCode currency;

    @Column(nullable = false)
    private long balanceMinor;

    @Version
    private long version;

    protected Account() {}

    public Account(Long id, CurrencyCode currency, long balanceMinor) {
        this.id = id;
        this.currency = currency;
        this.balanceMinor = balanceMinor;
    }

    public Long getId() { return id; }
    public CurrencyCode getCurrency() { return currency; }
    public long getBalanceMinor() { return balanceMinor; }
    public long getVersion() { return version; }

    public void withdraw(long amountMinor) {
        if (amountMinor <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (balanceMinor < amountMinor) throw new IllegalStateException("Insufficient funds");
        balanceMinor -= amountMinor;
    }

    public void deposit(long amountMinor) {
        if (amountMinor <= 0) throw new IllegalArgumentException("Amount must be positive");
        balanceMinor += amountMinor;
    }
}
