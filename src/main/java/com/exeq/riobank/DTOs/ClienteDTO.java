package com.exeq.riobank.DTOs;

import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.models.Loan;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

  private Long id;
  private String nombre, apellido, correo;
  private List<CuentaDTO> cuentas;
  private List<Loan> loans;
}
