package com.exeq.riobank.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private TransactionType type;
  private Double amount;
  private String description;
  private LocalDateTime date;
  @ManyToOne(fetch = FetchType.EAGER)
  private Cuenta cuenta;

  public Transaction(Long id, TransactionType type, Double amount, String description, LocalDateTime date){
    this.id = id;
    this.type = type;
    this.amount = amount;
    this.description = description;
    this.date = date;
  }


}
