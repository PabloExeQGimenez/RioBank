package com.exeq.riobank.DTOs;

import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.models.Cuenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ClienteDTO {

  private Long id;
  private String nombre, apellido, email;
  private List<CuentaDTO> cuentas;
  public ClienteDTO(Cliente cliente) {
    this.id = cliente.getId();
    this.nombre = cliente.getNombre();
    this.apellido = cliente.getApellido();
    this.email = cliente.getEmail();
    cuentas = cliente.getCuentas().stream().map(cuenta -> new CuentaDTO(cuenta)).collect(Collectors.toList());

  }


}
