package com.exeq.riobank.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cuenta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String numero;
  private LocalDate fechaCreacion;
  private Double balance;
  @ManyToOne(fetch = FetchType.EAGER)
  private Cliente cliente;
  @OneToMany(mappedBy = "cuenta", fetch = FetchType.EAGER)
  private List<Transaction> transactions;

  public Cuenta (String numero, LocalDate fechaCreacion, Double balance){
    this.numero = numero;
    this.fechaCreacion = fechaCreacion;
    this.balance = balance;
  }

  public void addTransaction(Transaction transaction) {
    transaction.setCuenta(this);
    this.transactions.add(transaction);
  }
}
