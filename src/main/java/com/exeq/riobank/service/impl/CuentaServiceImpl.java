package com.exeq.riobank.service.impl;

import com.exeq.riobank.models.Cuenta;
import com.exeq.riobank.models.CuentaTipo;
import com.exeq.riobank.repositories.CuentaRepo;
import com.exeq.riobank.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

  @Autowired
  private CuentaRepo cuentaRepo;
  @Override
  public Cuenta insertarCuenta(String numero, LocalDate fechaCreacion, Double balance, CuentaTipo tipo) {

    Cuenta cuenta = new Cuenta();
    cuenta.setNumero(numero);
    cuenta.setFechaCreacion(fechaCreacion);
    cuenta.setBalance(balance);
    cuenta.setTipo(tipo);

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

  @Override
  public Cuenta saveAccount(Cuenta cuenta) {
    return cuentaRepo.save(cuenta);
  }

  @Override
  public Cuenta findByNumero(String numero) {
    return cuentaRepo.findByNumero(numero).orElse(null);
  }
}
