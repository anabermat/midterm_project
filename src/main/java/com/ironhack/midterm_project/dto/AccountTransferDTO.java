package com.ironhack.midterm_project.dto;

import com.ironhack.midterm_project.model.user.AccountHolder;
import com.sun.istack.NotNull;

import javax.persistence.*;

public class AccountTransferDTO {

    private AccountHolder primaryOwner;


    private AccountHolder secondaryOwner;

    @NotNull
    private Long id;

    public AccountTransferDTO() {
    }

    public AccountTransferDTO(AccountHolder primaryOwner, AccountHolder secondaryOwner, Long id) {
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.id = id;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
