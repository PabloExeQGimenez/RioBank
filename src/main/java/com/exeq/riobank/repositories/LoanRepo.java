package com.exeq.riobank.repositories;

import com.exeq.riobank.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepo extends JpaRepository<Loan, Long> {
}
