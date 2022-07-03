package com.ironhack.midterm_project.controller.interfaces;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.dto.AccountBalanceDTO;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.account.Checking;
import com.ironhack.midterm_project.model.account.Savings;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AdminController {
    Account createCheckingAccount(Checking checkingAccount);

    Savings createSavingsAccount(Savings savingsAccount);

    Account findAccountById(Long id);

    Money findBalanceByAccountId(Long id);

    void updateBalance(Long id, AccountBalanceDTO accountBalanceDTO);
    void delete(Long id);
}
