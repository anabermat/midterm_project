package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.model.account.CreditCard;
import com.ironhack.midterm_project.model.account.Savings;
import com.ironhack.midterm_project.repository.CreditCardRepository;
import com.ironhack.midterm_project.service.interfaces.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public void updateBalanceByInterestRate(Long id, LocalDate lastUpdateInterestRate) {
        LocalDate now = LocalDate.now();
        long months = ChronoUnit.MONTHS.between(lastUpdateInterestRate, now);

        if(lastUpdateInterestRate == null || months > 1){

            CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            BigDecimal balance = creditCard.getBalance().getAmount();
            BigDecimal interestRate = creditCard.getInterestRate();
            Money newBalance = new Money();
            newBalance.increaseAmount(balance.multiply(interestRate.divide(BigDecimal.valueOf(12))));
            creditCard.setBalance(newBalance);
            creditCardRepository.save(creditCard);
            creditCard.setLastUpdateInterestRate(LocalDate.now());
        }
    }
}
