package com.exeq.riobank.service;

import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.models.Cuenta;

import org.springframework.stereotype.Service;

import java.util.List;


public interface ClienteService {
  Cliente insertarCliente(String nombre, String apellido, String email);
  List<Cliente> listadoClientes();
  void agregarCuenta(Cliente cliente, Cuenta cuenta);

}

// que queremos hacer con la clase Cliente
//luego lo implementamos