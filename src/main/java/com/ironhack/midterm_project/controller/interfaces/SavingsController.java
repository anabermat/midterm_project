package com.ironhack.midterm_project.controller.interfaces;

import com.ironhack.midterm_project.dto.InterestRateUpdateDateDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface SavingsController {
    void updateBalanceByInterestRate(Long id,InterestRateUpdateDateDTO interestRateUpdateDateDTO);

}
