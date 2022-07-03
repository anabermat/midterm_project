package com.ironhack.midterm_project.controller.impl;

import com.ironhack.midterm_project.controller.interfaces.SavingsController;
import com.ironhack.midterm_project.dto.AccountBalanceDTO;
import com.ironhack.midterm_project.dto.InterestRateUpdateDateDTO;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.repository.SavingsRepository;
import com.ironhack.midterm_project.service.interfaces.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SavingsControllerImpl implements SavingsController {

    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private SavingsService savingsService;

    @PatchMapping("/SavingsAccounts/{id}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalanceByInterestRate(@PathVariable Long id, @RequestBody InterestRateUpdateDateDTO interestRateUpdateDateDTO) {
        savingsService.updateBalanceByInterestRate(id, interestRateUpdateDateDTO.getLastUpdateInterestRate());
    }

}
