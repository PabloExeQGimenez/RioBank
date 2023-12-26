package com.exeq.riobank.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientLoanDTO {
  private Long clientLoanId;
  private Long loanId;
  private String name;
  private Double requestedAmount;
  private Integer feesToPay;

}
