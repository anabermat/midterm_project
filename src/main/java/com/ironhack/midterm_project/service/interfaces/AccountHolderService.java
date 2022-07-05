package com.ironhack.midterm_project.service.interfaces;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.dto.AccountTransferDTO;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.user.AccountHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AccountHolderService {
    Account findAccountById(Long id);
    void transferMoney(Long ownerId, Long transferId, AccountHolder primaryOwner, Money money);
}
