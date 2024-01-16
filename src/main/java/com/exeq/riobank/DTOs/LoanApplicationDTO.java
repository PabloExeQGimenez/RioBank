package com.exeq.riobank.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationDTO {
    private Long idLoan;
    private Double amount;
    private Integer payments;
    private String destinationAccount;
}
