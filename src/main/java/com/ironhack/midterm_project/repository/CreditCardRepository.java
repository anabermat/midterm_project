package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.account.CreditCard;
import com.ironhack.midterm_project.model.account.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
