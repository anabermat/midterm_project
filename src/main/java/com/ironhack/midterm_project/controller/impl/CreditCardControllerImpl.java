package com.ironhack.midterm_project.controller.impl;

import com.ironhack.midterm_project.controller.interfaces.CreditCardController;
import com.ironhack.midterm_project.dto.InterestRateUpdateDateDTO;
import com.ironhack.midterm_project.repository.CreditCardRepository;
import com.ironhack.midterm_project.repository.SavingsRepository;
import com.ironhack.midterm_project.service.interfaces.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreditCardControllerImpl implements CreditCardController {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CreditCardService creditCardService;

    @Override
    @PatchMapping("/CreditCards/{id}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalanceByInterestRate(@PathVariable Long id, @RequestBody InterestRateUpdateDateDTO interestRateUpdateDateDTO) {
        creditCardService.updateBalanceByInterestRate(id, interestRateUpdateDateDTO.getLastUpdateInterestRate());
    }
}
