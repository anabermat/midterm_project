package com.ironhack.midterm_project.controller.impl;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.controller.interfaces.AccountHolderController;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.repository.AccountRepository;
import com.ironhack.midterm_project.service.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountHolderControllerImpl implements AccountHolderController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHolderService accountHolderService;

    @GetMapping("/AccountHolderAccounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccountById(@PathVariable Long id) {
        return accountHolderService.findAccountById(id);
    }

    @GetMapping("/AccountHolderAccounts/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money findBalanceByAccountId(@PathVariable Long id) {
        Account account = accountHolderService.findAccountById(id);
        return account.getBalance();
    }


}
