package com.exeq.riobank.service;

import com.exeq.riobank.models.Card;
import com.exeq.riobank.models.CardColor;
import com.exeq.riobank.models.CardType;

import java.time.LocalDate;

public interface CardService {
  void saveCard(Card card);
}
