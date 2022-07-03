package com.ironhack.midterm_project.service.interfaces;

import com.ironhack.midterm_project.dto.InterestRateUpdateDateDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public interface SavingsService {
    void updateBalanceByInterestRate(Long id, LocalDate interestRateUpdateDate);
}
