package com.ironhack.midterm_project.dto;

import com.ironhack.midterm_project.classes.Money;
import com.sun.istack.NotNull;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

public class AccountBalanceDTO {
    @NotNull
    private Money balance;

    public AccountBalanceDTO() {
    }

    public AccountBalanceDTO(Money balance) {
        this.balance = balance;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
