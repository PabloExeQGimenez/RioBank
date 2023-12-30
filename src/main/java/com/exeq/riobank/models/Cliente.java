package com.exeq.riobank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @Column(name = "cliente_id")
  private Long id;
  private String nombre, apellido, email, password;
  @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
  private Set<Cuenta> cuentas = new HashSet<>();
  @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
  private List<ClientLoan> loans = new ArrayList<>();


  //@Temporal(TemporalType.DATE)

  public void agregarCuenta(Cuenta cuenta) {
    cuenta.setCliente(this);
    this.cuentas.add(cuenta);
  }
  public void addClientLoan(ClientLoan clientLoan) {
    clientLoan.setCliente(this);
    this.loans.add(clientLoan);
  }

}

