package com.exeq.riobank.DTOs;

import com.exeq.riobank.models.Cuenta;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDTO {

    private Long id;
    private String numero;
    private LocalDate fechaCreacion;
    private Double balance;
    private List<TransactionDTO> transactions;


}
