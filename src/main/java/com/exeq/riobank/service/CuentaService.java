package com.exeq.riobank.service;

import com.exeq.riobank.models.Cuenta;

import java.time.LocalDate;
import java.util.List;

public interface CuentaService {
  public Cuenta insertarCuenta(String numero, LocalDate fechaCreacion, Double balance);

  public List<Cuenta> listadoCuentas();
}
