package com.exeq.riobank.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

  private Loan (String name, Double maxAmount, List<Integer> payments){
    this.name = name;
    this.maxAmount = maxAmount;
    this.payments = payments;
  }
}
