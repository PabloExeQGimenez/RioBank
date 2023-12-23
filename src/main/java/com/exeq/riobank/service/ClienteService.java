package com.exeq.riobank.service;

import com.exeq.riobank.DTOs.ClienteDTO;
import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.models.Cuenta;

import org.springframework.stereotype.Service;

import java.util.List;


public interface ClienteService {
  Cliente insertarCliente(String nombre, String apellido, String email);
/*
  List<ClienteDTO> listadoClientes();
*/

  void saveClient(Cliente client);
  List<Cliente> listadoClientes();

}

// que queremos hacer con la clase Cliente
//luego lo implementamos