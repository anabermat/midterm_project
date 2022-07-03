package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.account.Savings;
import com.ironhack.midterm_project.model.account.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Long> {
}
