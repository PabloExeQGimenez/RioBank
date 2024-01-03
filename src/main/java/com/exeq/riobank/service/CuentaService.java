package com.exeq.riobank.service;

import com.exeq.riobank.models.Cuenta;

import java.time.LocalDate;
import java.util.List;

public interface CuentaService {
  Cuenta insertarCuenta(String numero, LocalDate fechaCreacion, Double balance);

  List<Cuenta> listadoCuentas();

  Cuenta cuentaId(Long id);

  Cuenta saveAccount(Cuenta cuenta);
}
