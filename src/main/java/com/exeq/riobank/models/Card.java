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

  public Card(String cardholder,CardType type, CardColor color, String number, String cvv, LocalDate thruDate, LocalDate fromDate){
    this.cardholder= cardholder;
    this.type = type;
    this.color = color;
    this.number = number;
    this.cvv = cvv;
    this.thruDate = thruDate;
    this.fromDate = fromDate;
  }
}
