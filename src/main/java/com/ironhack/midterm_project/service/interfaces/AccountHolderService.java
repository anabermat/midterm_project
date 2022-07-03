package com.ironhack.midterm_project.service.interfaces;

import com.ironhack.midterm_project.model.account.Account;

public interface AccountHolderService {
    Account findAccountById(Long id);
}
