package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.enums.Status;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.account.Checking;
import com.ironhack.midterm_project.model.account.Savings;
import com.ironhack.midterm_project.model.account.StudentChecking;
import com.ironhack.midterm_project.model.user.AccountHolder;
import com.ironhack.midterm_project.repository.AccountRepository;
import com.ironhack.midterm_project.repository.SavingsRepository;
import com.ironhack.midterm_project.service.interfaces.AdminService;
import com.ironhack.midterm_project.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import static com.ironhack.midterm_project.utils.Utils.convertToLocalDateViaInstant;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SavingsRepository savingsRepository;

    @Override
    public Account createCheckingAccount(Checking checkingAccount){
        Account account;
        LocalDate now = LocalDate.now();
        LocalDate dateOfBirth = convertToLocalDateViaInstant(checkingAccount.getPrimaryOwner().getDateOfBirth());
        long years = ChronoUnit.YEARS.between(dateOfBirth, now);

//        Money balance, Integer secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
//                Status status, Date creationDate
        if(years < 24){
            account = new StudentChecking(checkingAccount.getBalance(), checkingAccount.getSecretKey(),
                    checkingAccount.getPrimaryOwner(), checkingAccount.getSecondaryOwner(), checkingAccount.getStatus(),
                    checkingAccount.getCreationDate());
        } else{
//            Money balance, Integer secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
//                    Status status, Date creationDate, Date lastImportInterestRate
            account = new Checking(checkingAccount.getBalance(), checkingAccount.getSecretKey(),
                    checkingAccount.getPrimaryOwner(), checkingAccount.getSecondaryOwner(), checkingAccount.getStatus(),
                    checkingAccount.getCreationDate());
        }

        return accountRepository.save(account);
    }

    @Override
    public Savings createSavingsAccount(Savings savingsAccount) {
        Optional<Savings> optionalSavings = savingsRepository.findById(savingsAccount.getId());
        if(optionalSavings.isPresent()){
            return savingsRepository.save(savingsAccount);
        } else{
           throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @Override
    public Account findAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return account;
    }

    @Override
    public void updateBalance(Long id, Money balance) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        account.setBalance(balance);
        accountRepository.save(account);
    }

    @Override
    public void delete(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        accountRepository.delete(account);
    }
}
