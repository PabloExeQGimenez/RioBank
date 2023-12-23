package com.exeq.riobank.controllers;

import com.exeq.riobank.DTOs.ClienteDTO;
import com.exeq.riobank.mappers.ClienteMapper;
import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.repositories.ClienteRepo;
import com.exeq.riobank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  private ClienteMapper clienteMapper;

  @GetMapping("/clientes")
  public List<ClienteDTO> listadoClientes(){
       return clienteMapper.transformarAListaClienteDTO(clienteService.listadoClientes());
  }

  @GetMapping("/clientes/{id}")
  public ClienteDTO mostrarClienteId(@PathVariable Long id) {
    return clienteMapper.transformarAClienteDTO(clienteService.mostrarClienteId(id));
  }
}
