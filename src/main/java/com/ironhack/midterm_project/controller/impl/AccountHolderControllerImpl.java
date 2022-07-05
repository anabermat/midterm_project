package com.ironhack.midterm_project.controller.impl;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.controller.interfaces.AccountHolderController;
import com.ironhack.midterm_project.dto.AccountBalanceDTO;
import com.ironhack.midterm_project.dto.AccountTransferDTO;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.repository.AccountRepository;
import com.ironhack.midterm_project.service.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountHolderControllerImpl implements AccountHolderController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHolderService accountHolderService;

    @GetMapping("/AccountHolderAccounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccountById(@PathVariable @Valid Long id) {
        return accountHolderService.findAccountById(id);
    }

    @GetMapping("/AccountHolderAccounts/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money findBalanceByAccountId(@PathVariable @Valid Long id) {
        Account account = accountHolderService.findAccountById(id);
        return account.getBalance();
    }

    @PatchMapping("/AccountHolderAccounts/{id}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferMoney(@PathVariable @Valid Long id, @RequestBody AccountTransferDTO accountTransferDTO,
                              @RequestBody Money money) {
        accountHolderService.transferMoney(id, accountTransferDTO.getId(), accountTransferDTO.getPrimaryOwner(), money);
    }


}
