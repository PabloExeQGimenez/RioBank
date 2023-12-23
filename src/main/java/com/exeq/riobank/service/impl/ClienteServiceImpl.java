package com.exeq.riobank.service.impl;

import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.models.Cuenta;
import com.exeq.riobank.repositories.ClienteRepo;
import com.exeq.riobank.repositories.CuentaRepo;
import com.exeq.riobank.service.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private ClienteRepo clienteRepo;
  @Autowired
  private CuentaRepo cuentaRepo;
  @Override
  public Cliente insertarCliente(String nombre, String apellido, String email) {
    Cliente cliente= new Cliente();
    cliente.setNombre(nombre);
    cliente.setApellido(apellido);
    cliente.setEmail(email);
    return clienteRepo.save(cliente);
  }

  @Override
  public List<Cliente> listadoClientes() {
    return clienteRepo.findAll();
  }

  @Override
  @Transactional
  public void agregarCuenta(Cliente cliente, Cuenta cuenta) {
    cuenta.setCliente(cliente);
    cliente.getCuentas().add(cuenta);
  }
}
