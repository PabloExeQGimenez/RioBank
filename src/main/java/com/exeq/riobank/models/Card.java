package com.exeq.riobank.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class  Card {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String cardholder;
  private CardType type;
  private CardColor color;
  private String number;
  private String cvv;
  private LocalDate thruDate, fromDate;
  @ManyToOne(fetch = FetchType.EAGER)
  private Cliente cliente;
  private boolean active=true;

  public Card(String s, CardType cardType, CardColor cardColor, String s1, String s2, LocalDate now, LocalDate localDate) {
  }
}
