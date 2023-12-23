package com.exeq.riobank.DTOs;

import com.exeq.riobank.models.Cuenta;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private LocalDate fechaCreacion;
    private Double balance;






   /* public CuentaDTO(Cuenta cuenta){
      this.id = cuenta.getId();
      this.numero = cuenta.getNumero();
      this.fechaCreacion = cuenta.getFechaCreacion();
      this.balance = cuenta.getBalance();
    }*/
}
