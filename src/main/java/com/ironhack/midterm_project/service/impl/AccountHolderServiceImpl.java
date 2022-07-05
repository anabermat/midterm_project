package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.user.AccountHolder;
import com.ironhack.midterm_project.repository.AccountRepository;
import com.ironhack.midterm_project.service.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return account;
    }

    @Override
    public void transferMoney(Long ownerId, Long transferId, AccountHolder primaryOwner, Money money) {
        Account transferAccount = accountRepository.findById(transferId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Account ownerAccount = accountRepository.findById(ownerId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));;
        if(ownerAccount.getBalance().compareTo(money) > 0){
            ownerAccount.getBalance().decreaseAmount(money);
            transferAccount.getBalance().increaseAmount(money);
        }
    }
}
