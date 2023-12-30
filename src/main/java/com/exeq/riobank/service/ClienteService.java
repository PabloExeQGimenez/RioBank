package com.exeq.riobank.service;

import com.exeq.riobank.models.Cliente;

import java.util.List;
import java.util.Optional;


public interface ClienteService {
  Cliente insertarCliente(String nombre, String apellido, String email, String password);
  void saveClient(Cliente client);
  List<Cliente> listadoClientes();
  Cliente mostrarClienteId(Long id);

  Cliente buscarClientePorEmail(String email);
}

// que queremos hacer con la clase Cliente
//luego lo implementamos