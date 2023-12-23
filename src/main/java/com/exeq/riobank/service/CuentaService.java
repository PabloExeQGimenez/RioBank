package com.exeq.riobank.service;

import com.exeq.riobank.models.Cuenta;

import java.util.Date;
import java.util.List;

public interface CuentaService {
  public Cuenta insertarCuenta(String numero, Date fechaCreacion, Double balance);

  public List<Cuenta> listadoCuentas();
}
