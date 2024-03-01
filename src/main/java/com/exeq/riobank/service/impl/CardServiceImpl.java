package com.exeq.riobank.service.impl;

import com.exeq.riobank.models.Card;
import com.exeq.riobank.models.CardColor;
import com.exeq.riobank.models.CardType;
import com.exeq.riobank.repositories.CardRepo;
import com.exeq.riobank.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
  @Autowired
  private CardRepo cardRepo;
  @Override
  public Card createCard(CardType type, CardColor color, String number, String cVV, LocalDate thruDate, LocalDate fromDate) {
    Card card = new Card();
    card.setType(type);
    card.setColor(color);
    card.setNumber(number);
    card.setCvv(cVV);
    card.setThruDate(thruDate);
    card.setFromDate(fromDate);
    return cardRepo.save(card);
  }

  @Override
  public List<Card> getCards() {
    return cardRepo.findAll();
  }

  @Override
  public void saveCard(Card card) {

    cardRepo.save(card);
  }


}
