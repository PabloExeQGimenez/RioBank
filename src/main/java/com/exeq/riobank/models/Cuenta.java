package com.exeq.riobank.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
  private Set<Transaction> transactions = new HashSet<>();
  private boolean active = true;
  private CuentaTipo tipo;

  public Cuenta (String numero, LocalDate fechaCreacion, Double balance, CuentaTipo tipo){
    this.numero = numero;
    this.fechaCreacion = fechaCreacion;
    this.balance = balance;
    this.tipo = tipo;
  }

  public void addTransaction(Transaction transaction) {
    transaction.setCuenta(this);
    this.transactions.add(transaction);
  }
}
