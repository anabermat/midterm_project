package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.account.CreditCard;
import com.ironhack.midterm_project.model.user.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {
}
