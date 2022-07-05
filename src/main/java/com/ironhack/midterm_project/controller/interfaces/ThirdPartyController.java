package com.ironhack.midterm_project.controller.interfaces;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.dto.AccountTransferDTO;

public interface ThirdPartyController {

    void transferMoney (Long id, AccountTransferDTO accountTransferDTO, Money money);
}
