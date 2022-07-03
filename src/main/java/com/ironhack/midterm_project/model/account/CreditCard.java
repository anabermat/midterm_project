package com.ironhack.midterm_project.model.account;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.enums.Status;
import com.ironhack.midterm_project.model.user.AccountHolder;
import com.sun.istack.NotNull;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account {

    @Embedded
    private Money creditLimit;

    @NotNull
    private BigDecimal interestRate;

    // crear fecha de interest rate

    private LocalDate lastUpdateInterestRate;


    public CreditCard() {

    }

    // constructor lleno
    public CreditCard(Money balance, Integer secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Status status, Money creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    // constructor con credit limit e interest rate default
    public CreditCard(Money balance, Integer secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                      Status status) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status);
        this.creditLimit = new Money(BigDecimal.valueOf(100));
        this.interestRate = BigDecimal.valueOf(0.2);
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        if(creditLimit.getAmount().compareTo(BigDecimal.valueOf(100000)) == 0 ||
                creditLimit.getAmount().compareTo(BigDecimal.valueOf(100000)) == -1){
            this.creditLimit = creditLimit;
        }else{
            throw new StringIndexOutOfBoundsException("This number is out of the expected range");
        }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate.compareTo(BigDecimal.valueOf(0.2)) == 0 ||
                interestRate.compareTo(BigDecimal.valueOf(0.2)) == 1){
            this.interestRate = interestRate;
        }else{
            throw new StringIndexOutOfBoundsException("This number is out of the expected range");
        }
    }

    public LocalDate getLastUpdateInterestRate() {
        return lastUpdateInterestRate;
    }

    public void setLastUpdateInterestRate(LocalDate lastUpdateInterestRate) {
        this.lastUpdateInterestRate = LocalDate.now();
    }
}
