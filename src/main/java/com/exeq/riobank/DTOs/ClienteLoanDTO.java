package com.exeq.riobank.DTOs;

import com.exeq.riobank.models.ClientLoan;
import com.exeq.riobank.models.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteLoanDTO {
  private Long id;
  private Long loanId;
  private String loanName;
  private Double amount;
  private Integer payments;

    public ClienteLoanDTO(ClientLoan clientLoan) {
    this.id = clientLoan.getId();
    this.loanId = clientLoan.getLoan().getId();
    this.loanName = clientLoan.getLoan().getName();
    this.amount = clientLoan.getAmount();
    this.payments = clientLoan.getPayments();
  }
}
