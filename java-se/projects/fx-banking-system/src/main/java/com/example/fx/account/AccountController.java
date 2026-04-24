package com.example.fx.account;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountView> all() {
        return accountService.findAll();
    }

    @PostMapping("/{id}/withdraw")
    public AccountView withdraw(@PathVariable Long id, @Valid @RequestBody MoneyRequest request) {
        return accountService.withdraw(id, request.amountMinor());
    }

    @PostMapping("/{id}/deposit")
    public AccountView deposit(@PathVariable Long id, @Valid @RequestBody MoneyRequest request) {
        return accountService.deposit(id, request.amountMinor());
    }

    public record MoneyRequest(@Positive long amountMinor) {}
    public record AccountView(Long id, CurrencyCode currency, long balanceMinor, long version) {}
}
