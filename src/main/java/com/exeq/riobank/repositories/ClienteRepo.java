package com.exeq.riobank.repositories;

import com.exeq.riobank.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClienteRepo extends JpaRepository<Cliente, Long> {
}
