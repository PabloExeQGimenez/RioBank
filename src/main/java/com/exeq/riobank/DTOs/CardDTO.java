package com.exeq.riobank.DTOs;

import com.exeq.riobank.models.Card;
import com.exeq.riobank.models.CardColor;
import com.exeq.riobank.models.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter

@NoArgsConstructor
public class CardDTO {
  private Long id;
  private String cardholder;
  private CardType type;
  private CardColor color;
  private String number;
  private String cvv;
  private LocalDate thruDate, fromDate;

  public CardDTO(Card card){
    id = card.getId();
    cardholder = card.getCardholder();
    type = card.getType();
    color = card.getColor();
    number = card.getNumber();
    cvv = card.getCvv();
    thruDate=card.getThruDate();
    fromDate = card.getFromDate();
  }

}
