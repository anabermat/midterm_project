package com.ironhack.midterm_project.controller.interfaces;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.dto.AccountTransferDTO;
import com.ironhack.midterm_project.model.account.Account;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AccountHolderController {
    Account findAccountById(Long id);

    Money findBalanceByAccountId(Long id);

    void transferMoney (Long id,AccountTransferDTO accountTransferDTO, Money money);
}
