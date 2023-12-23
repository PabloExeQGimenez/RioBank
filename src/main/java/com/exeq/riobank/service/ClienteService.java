package com.exeq.riobank.service;

import com.exeq.riobank.models.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClienteService {
  public Cliente insertarCliente(String nombre, String apellido, String email);
  public List<Cliente> listadoClientes();

}

// que queremos hacer con la clase Cliente
//luego lo implementamos