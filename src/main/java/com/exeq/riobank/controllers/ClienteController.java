package com.exeq.riobank.controllers;

import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @GetMapping("/cliente")
  public List<Cliente> listadoClientes(){
    List<Cliente> clientes = clienteService.listadoClientes();
    return clientes;
  }
}
