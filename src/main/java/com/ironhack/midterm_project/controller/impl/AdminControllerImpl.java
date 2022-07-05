package com.ironhack.midterm_project.controller.impl;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.controller.interfaces.AdminController;
import com.ironhack.midterm_project.dto.AccountBalanceDTO;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.account.Checking;
import com.ironhack.midterm_project.model.account.Savings;
import com.ironhack.midterm_project.repository.AccountRepository;
import com.ironhack.midterm_project.repository.AdminRepository;
import com.ironhack.midterm_project.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Optional;

@RestController
public class AdminControllerImpl implements AdminController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AdminService adminService;


    @GetMapping("/AdminAccounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccountById(@PathVariable @Valid Long id) {
        return adminService.findAccountById(id);
    }

    @GetMapping("/AdminAccounts/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money findBalanceByAccountId(@PathVariable @Valid Long id) {
        Account account = adminService.findAccountById(id);
        return account.getBalance();
    }

    @PatchMapping("/AdminAccounts/{id}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance(@PathVariable @Valid Long id, @RequestBody AccountBalanceDTO accountBalanceDTO) {
        adminService.updateBalance(id, accountBalanceDTO.getBalance());
    }

    @PostMapping("/AdminAccounts/CheckingAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingAccount(@RequestBody Checking checkingAccount) {
        return adminService.createCheckingAccount(checkingAccount);
    }

    @PostMapping("/AdminAccounts/SavingAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavingsAccount(@RequestBody Savings savingsAccount) {
        return adminService.createSavingsAccount(savingsAccount);
    }
    
    @PostMapping("/AdminAccounts/CreditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCreditCard(@RequestBody Checking checkingAccount) {
        return adminService.createCheckingAccount(checkingAccount);
    }

    @DeleteMapping("/AdminAccounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Valid Long id) {
        adminService.delete(id);
    }


}
