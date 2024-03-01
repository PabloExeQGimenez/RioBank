package com.exeq.riobank.service;

import com.exeq.riobank.models.Card;
import com.exeq.riobank.models.CardColor;
import com.exeq.riobank.models.CardType;

import java.time.LocalDate;
import java.util.List;

public interface CardService {
  void saveCard(Card card);
  Card createCard(CardType type, CardColor color, String number, String cVV, LocalDate thruDate, LocalDate fromDate);
  List<Card> getCards();
}
