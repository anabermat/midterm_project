package com.ironhack.midterm_project.model.account;

import com.ironhack.midterm_project.model.user.AccountHolder;
import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.enums.Status;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account {
    private Date creationDate;

    private Date lastUpdateInterestRate;

    public StudentChecking() {
    }

    public StudentChecking(Money balance, Integer secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                           Status status, Date creationDate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status);
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateInterestRate() {
        return lastUpdateInterestRate;
    }

    public void setLastUpdateInterestRate(Date lastUpdateInterestRate) {
        this.lastUpdateInterestRate = lastUpdateInterestRate;
    }
}
