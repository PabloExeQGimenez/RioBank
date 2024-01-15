package com.exeq.riobank.controllers;

import com.exeq.riobank.DTOs.CuentaDTO;
import com.exeq.riobank.mappers.CuentaMapper;
import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.models.Cuenta;
import com.exeq.riobank.service.ClienteService;
import com.exeq.riobank.service.CuentaService;
import com.exeq.riobank.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

import static com.exeq.riobank.utils.AccountUtils.generateRandomNumber;

@RestController
@RequestMapping("/api")
public class CuentaController {

  @Autowired
  private CuentaService cuentaService;
  @Autowired
  private CuentaMapper cuentaMapper;
  @Autowired
  private ClienteService clienteService;
  @Autowired
  private AccountUtils accountUtils;

  @GetMapping("/cuentas")
  public List<CuentaDTO> listadoCuentas(){
    return cuentaMapper.transformarAListaCuentaDTO(cuentaService.listadoCuentas());
  }

  @GetMapping("/cuentas/{id}")
  public CuentaDTO cuentaId(@PathVariable Long id){
    return cuentaMapper.transformarACuentaDTO(cuentaService.cuentaId(id));
  }
  @PostMapping("/clientes/current/accounts")
  public ResponseEntity<Object> createAccount(Authentication authentication){
    Cliente autenticado = clienteService.buscarClientePorEmail(authentication.getName());
    if(autenticado.getCuentas().size() >= 3){
      return new ResponseEntity<>("You can have up to three active accounts", HttpStatus.FORBIDDEN);
    }
    Cuenta cuenta = new Cuenta(generateRandomNumber(), LocalDate.now(), 0.00 );
    autenticado.agregarCuenta(cuenta);
    cuentaService.saveAccount(cuenta);
    clienteService.saveClient(autenticado);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
