package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.account.Checking;
import com.ironhack.midterm_project.model.user.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
}
