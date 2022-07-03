package com.ironhack.midterm_project.service.interfaces;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.dto.AccountBalanceDTO;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.account.Checking;
import com.ironhack.midterm_project.model.account.Savings;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AdminService {
    Account createCheckingAccount(Checking checkingAccount);
    Savings createSavingsAccount(Savings savingsAccount);

    Account findAccountById(Long id);

    void updateBalance(Long id, Money balance);

    void delete(Long id);
}
