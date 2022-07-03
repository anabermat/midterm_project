package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
