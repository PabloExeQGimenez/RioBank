package com.exeq.riobank.controllers;

import com.exeq.riobank.DTOs.ClienteDTO;
import com.exeq.riobank.mappers.ClienteMapper;
import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.models.Cuenta;
import com.exeq.riobank.models.CuentaTipo;
import com.exeq.riobank.repositories.ClienteRepo;
import com.exeq.riobank.service.ClienteService;
import com.exeq.riobank.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.exeq.riobank.utils.AccountUtils.generateRandomNumber;

@RestController
@RequestMapping("/api")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;
  @Autowired
  private ClienteMapper clienteMapper;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private ClienteRepo clienteRepo;
  @Autowired
  private CuentaService cuentaService;

  @GetMapping("/clientes")
  public List<ClienteDTO> listadoClientes(){
       return clienteMapper.transformarAListaClienteDTO(clienteService.listadoClientes());
  }

  @GetMapping("/clientes/{id}")
  public ClienteDTO mostrarClienteId(@PathVariable Long id) {
    return clienteMapper.transformarAClienteDTO(clienteService.mostrarClienteId(id));
  }

  @PostMapping("/clientes")
  public ResponseEntity<Object> register(@RequestParam String nombre, @RequestParam String apellido,
                                         @RequestParam String email, @RequestParam String password){

    Cliente cliente = clienteService.insertarCliente(nombre, apellido, email, passwordEncoder.encode(password));
    Cuenta cuenta = new Cuenta(generateRandomNumber(), LocalDate.now(), 0.00, CuentaTipo.SAVINGS); ;
    cliente.agregarCuenta(cuenta);
    cuentaService.saveAccount(cuenta);
    clienteService.saveClient(cliente);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/clientes/current")
  public ClienteDTO clienteActual(Authentication authentication){

    return clienteMapper.transformarAClienteDTO(clienteService.buscarClientePorEmail(authentication.getName()));
  }
}
