package com.exeq.riobank.repositories;

import com.exeq.riobank.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CuentaRepo extends JpaRepository<Cuenta, Long> {
}
