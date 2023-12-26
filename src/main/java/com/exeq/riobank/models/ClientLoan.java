package com.exeq.riobank.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientLoan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Double amount;
  private Integer payments;
  @ManyToOne()
  private Cliente cliente;
  @ManyToOne()
  private Loan loan;

  public ClientLoan(Loan loan, Double amount, Integer payments){
    this.loan = loan;
    this.amount = amount;
    this.payments = payments;
  }

}
