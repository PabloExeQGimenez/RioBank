package com.exeq.riobank.service;

import com.exeq.riobank.models.Cliente;

import java.util.List;


public interface ClienteService {
  Cliente insertarCliente(String nombre, String apellido, String email);
  void saveClient(Cliente client);
  List<Cliente> listadoClientes();
  Cliente mostrarClienteId(Long id);
}

// que queremos hacer con la clase Cliente
//luego lo implementamos