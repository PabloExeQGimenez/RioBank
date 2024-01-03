package com.exeq.riobank.service.impl;

import com.exeq.riobank.models.Card;
import com.exeq.riobank.models.CardColor;
import com.exeq.riobank.models.CardType;
import com.exeq.riobank.service.CardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CardServiceImpl implements CardService {
  @Override
  public Card createCard(CardType type, CardColor color, String number, String cVV, LocalDate thruDate, LocalDate fromDate) {
    return new Card();
  }
}
