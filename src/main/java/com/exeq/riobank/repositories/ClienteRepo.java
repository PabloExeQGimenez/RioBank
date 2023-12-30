package com.exeq.riobank.repositories;

import com.exeq.riobank.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ClienteRepo extends JpaRepository<Cliente, Long> {
  Optional<Cliente> findByEmail(String email);
}
