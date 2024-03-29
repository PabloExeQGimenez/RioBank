package com.exeq.riobank.repositories;

import com.exeq.riobank.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface LoanRepo extends JpaRepository<Loan, Long> {
    Loan findById(long id);

    @Override
    List<Loan> findAll();
}
