package com.exeq.riobank.controllers;

import com.exeq.riobank.DTOs.ClienteDTO;
import com.exeq.riobank.repositories.ClienteRepo;
import com.exeq.riobank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;
  @Autowired
  private ClienteRepo clienteRepo;

  @GetMapping("/clientes")
  public List<ClienteDTO> listadoClientes(){
    List<ClienteDTO> clientes = clienteService.listadoClientes().stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
    return clientes;
  }
}
