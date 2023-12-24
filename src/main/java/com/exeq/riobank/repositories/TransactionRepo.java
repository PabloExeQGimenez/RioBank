package com.exeq.riobank.repositories;

import com.exeq.riobank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}
