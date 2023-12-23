package com.exeq.riobank.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Cuenta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String numero;
  private Date fechaCreacion;
  private Double balance;
  @ManyToOne(fetch = FetchType.EAGER)
  private Cliente cliente;

  public Cuenta (String numero, Date fechaCreacion, Double balance){
    this.numero = numero;
    this.fechaCreacion = fechaCreacion;
    this.balance = balance;
  }
}
