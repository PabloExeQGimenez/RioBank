package com.exeq.riobank.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  private Loan (String name, Double maxAmount, List<Integer> payments){
    this.name = name;
    this.maxAmount = maxAmount;
    this.payments = payments;
  }
}
