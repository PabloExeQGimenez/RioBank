package com.exeq.riobank.DTOs;

import com.exeq.riobank.models.Card;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
  private Long id;
  private String nombre, apellido, email;
  private List<CuentaDTO> cuentas;
  private List<ClienteLoanDTO> loans;
  private List<CardDTO> cards;
}
