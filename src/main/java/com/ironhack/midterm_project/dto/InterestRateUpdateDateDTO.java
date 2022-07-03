package com.ironhack.midterm_project.dto;

import java.time.LocalDate;
import java.util.Date;

public class InterestRateUpdateDateDTO {

    private LocalDate lastUpdateInterestRate;

    public InterestRateUpdateDateDTO() {
    }

    public InterestRateUpdateDateDTO(LocalDate lastUpdateInterestRate) {
        this.lastUpdateInterestRate = lastUpdateInterestRate;
    }

    public LocalDate getLastUpdateInterestRate() {
        return lastUpdateInterestRate;
    }

    public void setLastUpdateInterestRate(LocalDate lastUpdateInterestRate) {
        this.lastUpdateInterestRate = lastUpdateInterestRate;
    }
}
