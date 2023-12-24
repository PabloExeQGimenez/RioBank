package com.exeq.riobank.service.impl;

import com.exeq.riobank.models.Cuenta;
import com.exeq.riobank.repositories.CuentaRepo;
import com.exeq.riobank.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

  @Autowired
  private CuentaRepo cuentaRepo;
  @Override
  public Cuenta insertarCuenta(String numero, LocalDate fechaCreacion, Double balance) {

    Cuenta cuenta = new Cuenta();
    cuenta.setNumero(numero);
    cuenta.setFechaCreacion(fechaCreacion);
    cuenta.setBalance(balance);

    return cuentaRepo.save(cuenta);
  }
  @Override
  public List<Cuenta> listadoCuentas() {
    return cuentaRepo.findAll();
  }

  @Override
  public Cuenta cuentaId(Long id) {
    return cuentaRepo.findById(id).orElse(null);
  }
}
