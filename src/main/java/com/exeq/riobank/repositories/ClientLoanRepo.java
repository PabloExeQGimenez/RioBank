package com.exeq.riobank.repositories;

import com.exeq.riobank.models.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientLoanRepo extends JpaRepository<ClientLoan, Long> {
}
