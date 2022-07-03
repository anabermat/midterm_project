package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.account.Savings;
import com.ironhack.midterm_project.repository.SavingsRepository;
import com.ironhack.midterm_project.service.interfaces.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import static com.ironhack.midterm_project.utils.Utils.convertToLocalDateViaInstant;

@Service
public class SavingsServiceImpl implements SavingsService {

    @Autowired
    private SavingsRepository savingsRepository;

    @Override
    public void updateBalanceByInterestRate(Long id, LocalDate interestRateUpdateDate) {
        LocalDate now = LocalDate.now();
        long months = ChronoUnit.MONTHS.between(interestRateUpdateDate, now);

        if(interestRateUpdateDate == null || months > 12){

            Savings savings = savingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            BigDecimal balance = savings.getBalance().getAmount();
            BigDecimal interestRate = savings.getInterestRate();
            Money newBalance = new Money();
            newBalance.increaseAmount(balance.multiply(interestRate));
            savings.setBalance(newBalance);
            savingsRepository.save(savings);
            savings.setLastUpdateInterestRate(LocalDate.now());
        }

    }
}
