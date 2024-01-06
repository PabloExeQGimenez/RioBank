package com.exeq.riobank.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity @Table(name = "Cliente")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre, apellido, email, password;
  @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
  private Set<Cuenta> cuentas = new HashSet<>();
  @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
  private List<ClientLoan> loans = new ArrayList<>();
  @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
  private List<Card> cards = new ArrayList<>();

  public void agregarCuenta(Cuenta cuenta) {
    cuenta.setCliente(this);
    this.cuentas.add(cuenta);
  }
  public void addClientLoan(ClientLoan clientLoan) {
    clientLoan.setCliente(this);
    this.loans.add(clientLoan);
  }
  public void addCard(Card card) {
    card.setCliente(this);
    this.cards.add(card);
  }

}

