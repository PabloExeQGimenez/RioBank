package com.exeq.riobank.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientLoan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Double amount;
  private Integer payments;
  @ManyToOne(fetch = FetchType.EAGER)
  private Cliente cliente;
  @ManyToOne(fetch = FetchType.EAGER)
  private Loan loan;
  public ClientLoan(Double amount, Integer payments){
    this.amount = amount;
    this.payments = payments;
  }

}
