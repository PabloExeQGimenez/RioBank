package com.exeq.riobank.controllers;

import com.exeq.riobank.DTOs.CuentaDTO;
import com.exeq.riobank.mappers.CuentaMapper;
import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.models.Cuenta;
import com.exeq.riobank.models.CuentaTipo;
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
import java.util.stream.Collectors;

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
  public List<CuentaDTO> listadoCuentas() {
    // Obtener la lista de cuentas activas del servicio
    List<CuentaDTO> cuentaDTOs = cuentaService.listadoCuentas().stream()
            .filter(Cuenta::isActive)  // Filtrar por isActive
            .map(account -> cuentaMapper.transformarACuentaDTO(account))
            .collect(Collectors.toList());

    // Devolver la lista de CuentaDTO
    return cuentaDTOs;
  }

  @GetMapping("/cuentas/{id}")
  public CuentaDTO cuentaId(@PathVariable Long id){
    return cuentaMapper.transformarACuentaDTO(cuentaService.cuentaId(id));
  }
  @PostMapping("/clientes/current/accounts")
  public ResponseEntity<Object> createAccount(Authentication authentication, @RequestParam String tipo){
    Cliente autenticado = clienteService.buscarClientePorEmail(authentication.getName());
    if(autenticado.getCuentas().size() >= 10){
      return new ResponseEntity<>("You can have up to three active accounts", HttpStatus.FORBIDDEN);
    }

    Cuenta cuenta = new Cuenta(generateRandomNumber(), LocalDate.now(), 0.00, CuentaTipo.valueOf(tipo));
    autenticado.agregarCuenta(cuenta);
    cuentaService.saveAccount(cuenta);
    clienteService.saveClient(autenticado);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
    @PostMapping("/cuentas/{accountId}")
  public ResponseEntity<Object> deleteAccount(@PathVariable Long accountId){
    Cuenta cuenta = cuentaService.cuentaId(accountId);
    if(cuenta.getBalance() > 0){
      return new ResponseEntity<>("You can't delete an account with a balance greater than 0", HttpStatus.FORBIDDEN);
    }
    cuenta.setActive(false);
    cuentaService.saveAccount(cuenta);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/clientes/current/accounts")
    public List<CuentaDTO> listadoCuentasCliente(Authentication authentication){
        Cliente autenticado = clienteService.buscarClientePorEmail(authentication.getName());


        return autenticado.getCuentas().stream()
                .filter(Cuenta::isActive)
                .map(account -> cuentaMapper.transformarACuentaDTO(account))
                .collect(Collectors.toList());
    }
}
