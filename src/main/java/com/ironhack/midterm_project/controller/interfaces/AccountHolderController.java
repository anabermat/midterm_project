package com.ironhack.midterm_project.controller.interfaces;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.model.account.Account;

public interface AccountHolderController {
    Account findAccountById(Long id);

    Money findBalanceByAccountId(Long id);
}
