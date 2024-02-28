package com.exeq.riobank.service;

import com.exeq.riobank.models.Cuenta;
import com.exeq.riobank.models.CuentaTipo;

import java.time.LocalDate;
import java.util.List;

public interface CuentaService {
  Cuenta insertarCuenta(String numero, LocalDate fechaCreacion, Double balance, CuentaTipo tipo);

  List<Cuenta> listadoCuentas();

  Cuenta cuentaId(Long id);

  Cuenta saveAccount(Cuenta cuenta);

  Cuenta findByNumero(String numero);
}
