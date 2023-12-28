package com.exeq.riobank.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Double maxAmount;
  @ElementCollection
  private List<Integer> payments = new ArrayList<>();
  @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
  private Set<ClientLoan> clientLoans = new HashSet<>();

  public Loan(String name, Double maxAmount, List<Integer> payments){
    this.name = name;
    this.maxAmount = maxAmount;
    this.payments = payments;
  }
  public void addClientLoan(ClientLoan clientLoan) {
    clientLoan.setLoan(this);
    this.clientLoans.add(clientLoan);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
