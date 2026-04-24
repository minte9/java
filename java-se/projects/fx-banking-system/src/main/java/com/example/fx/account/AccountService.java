package com.example.fx.account;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountController.AccountView> findAll() {
        return accountRepository.findAll().stream().map(this::toView).toList();
    }

    @Transactional
    public AccountController.AccountView withdraw(Long id, long amountMinor) {
        Account account = accountRepository.findByIdForUpdate(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + id));
        account.withdraw(amountMinor);
        return toView(account);
    }

    @Transactional
    public AccountController.AccountView deposit(Long id, long amountMinor) {
        Account account = accountRepository.findByIdForUpdate(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + id));
        account.deposit(amountMinor);
        return toView(account);
    }

    private AccountController.AccountView toView(Account a) {
        return new AccountController.AccountView(a.getId(), a.getCurrency(), a.getBalanceMinor(), a.getVersion());
    }
}
