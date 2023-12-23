package com.exeq.riobank.repositories;

import com.exeq.riobank.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepo extends JpaRepository<Cuenta, Long> {
}
