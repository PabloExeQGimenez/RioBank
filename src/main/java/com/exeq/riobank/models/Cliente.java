package com.exeq.riobank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity @Table(name = "Cliente")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cliente_id")
  private Long id;
  private String nombre, apellido, email;
  @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
  private Set<Cuenta> cuentas = new HashSet<>();
  @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
  private Set<ClientLoan> clientLoans = new HashSet<>();


  //@Temporal(TemporalType.DATE)

  public void agregarCuenta(Cuenta cuenta) {
    cuenta.setCliente(this);
    this.cuentas.add(cuenta);
  }

}

