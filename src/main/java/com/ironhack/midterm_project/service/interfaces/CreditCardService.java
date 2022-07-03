package com.ironhack.midterm_project.service.interfaces;

import java.time.LocalDate;

public interface CreditCardService {
    void updateBalanceByInterestRate(Long id, LocalDate lastUpdateInterestRate);
}
