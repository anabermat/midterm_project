package com.ironhack.midterm_project.model.account;

import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.enums.Status;
import com.ironhack.midterm_project.model.user.AccountHolder;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Savings extends Account {
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
    })
    private Money minimumBalance;

    @NotNull
    private BigDecimal interestRate;

    private Date creationDate;

    private LocalDate lastUpdateInterestRate;

    // constructor vacío
    public Savings() {

    }

    // constructo lleno entero que te permita settearlo al valor que sea
    // hacer que se lo pase al setter para comprobar conidiciones

    public Savings(Money balance, Integer secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Status status, Money minimumBalance, BigDecimal interestRate, Date creationDate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
        this.creationDate = creationDate;
    }

    // constructor con interest rate y minimum balance default
    public Savings(Money balance, Integer secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Status status, Date creationDate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status);
        this.minimumBalance.decreaseAmount(BigDecimal.valueOf(1000));
        this.interestRate = BigDecimal.valueOf(0.0025);
        this.creationDate = creationDate;
    }


    public Money getMinimumBalance() {
        return minimumBalance;
    }



    public void setMinimumBalance(Money minimumBalance) {
            if(minimumBalance.getAmount().compareTo(BigDecimal.valueOf(100)) == 0 ||
                    minimumBalance.getAmount().compareTo(BigDecimal.valueOf(100)) == 1){
                this.minimumBalance = minimumBalance;
            }else{
               throw new StringIndexOutOfBoundsException("This number is out of the expected range");
            }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    // poner en el setter las restricciones de interest rate con exception
    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate.compareTo(BigDecimal.valueOf(0.5)) == 0 ||
                interestRate.compareTo(BigDecimal.valueOf(0.5)) == -1){
            this.interestRate = interestRate;
        }else{
            throw new StringIndexOutOfBoundsException("This number is out of the expected range");
        }
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
