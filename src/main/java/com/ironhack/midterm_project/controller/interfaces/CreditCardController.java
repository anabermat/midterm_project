package com.ironhack.midterm_project.controller.interfaces;

import com.ironhack.midterm_project.dto.InterestRateUpdateDateDTO;

public interface CreditCardController {
    void updateBalanceByInterestRate(Long id, InterestRateUpdateDateDTO interestRateUpdateDateDTO);
}
