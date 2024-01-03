package com.exeq.riobank.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String cardholder;
  private CardType type;
  private CardColor color;
  private String number;
  private String cvv;
  private LocalDate thruDate, fromDate;

  public Card(CardType type, CardColor color, String number, String cvv, LocalDate thruDate, LocalDate fromDate){
    this.type = type;
    this.color = color;
    this.number = number;
    this.cvv = cvv;
    this.thruDate = thruDate;
    this.fromDate = fromDate;


  }

  public void setCliente(Cliente cliente) {
  }
}
