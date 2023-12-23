package com.exeq.riobank.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "Cliente")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cliente_id")
  private Long id;
  private String nombre, apellido, email;
  @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
  private Set<Cuenta> cuentas = new HashSet<>();


  //@Temporal(TemporalType.DATE)

  public Cliente(String nombre, String apellido, String email){
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
  }

  public void agregarCuenta(Cuenta cuenta) {
    cuenta.setCliente(this);
    this.cuentas.add(cuenta);
  }
}

