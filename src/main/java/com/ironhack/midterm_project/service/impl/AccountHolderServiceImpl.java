package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.repository.AccountRepository;
import com.ironhack.midterm_project.service.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return account;
    }
}
