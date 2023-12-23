package com.exeq.riobank.DTOs;

import com.exeq.riobank.models.Cliente;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {

  private Long id;
  private String nombre, apellido, email;
  private List<CuentaDTO> cuentas;
}
