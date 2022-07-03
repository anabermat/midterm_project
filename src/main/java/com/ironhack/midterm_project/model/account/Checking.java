package com.ironhack.midterm_project.model.account;

import com.ironhack.midterm_project.model.user.AccountHolder;
import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.enums.Status;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account {
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
    })
    private Money minimumBalance;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "penaltyFee_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "penaltyFee_currency"))
    })
    private Money monthlyMaintenanceFee;

    //he elegido el .utils por si me da error
    private Date creationDate;

    // crear fecha de interest rate
    private LocalDate lastUpdateInterestRate;

    public Checking() {
    }

    //Constructor que pone el minimum balance y el maintenance Fee en default
    public Checking(Money balance, Integer secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                    Status status, Date creationDate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status);
        this.minimumBalance = new Money(BigDecimal.valueOf(250));
        this.monthlyMaintenanceFee = new Money(BigDecimal.valueOf(12));
        this.creationDate = creationDate;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }


    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public LocalDate getLastUpdateInterestRate() {
        return lastUpdateInterestRate;
    }

    public void setLastUpdateInterestRate(LocalDate lastUpdateInterestRate) {
        this.lastUpdateInterestRate = LocalDate.now();
    }

    @Override
    public void setBalance(Money balance) {
        if(balance.compareTo(minimumBalance)<0){
            balance.decreaseAmount(BigDecimal.valueOf(40));
        }
        super.setBalance(balance);
    }
}
