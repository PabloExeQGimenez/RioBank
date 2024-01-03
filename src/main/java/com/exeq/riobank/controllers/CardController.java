package com.exeq.riobank.controllers;

import com.exeq.riobank.models.CardColor;
import com.exeq.riobank.models.CardType;
import com.exeq.riobank.models.Card;

import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.service.CardService;
import com.exeq.riobank.service.ClienteService;
import com.exeq.riobank.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.exeq.riobank.utils.CardUtils.generateCvv;
import static com.exeq.riobank.utils.CardUtils.generateNumber;


@RestController
@RequestMapping("/api")
public class CardController {

  @Autowired
  private CardService cardService;
  @Autowired
  private ClienteService clienteService;
  @Autowired
  private CardUtils cardUtils;

  @PostMapping("/clientes/current/cards")
  public Card createCard(@RequestParam CardType type, @RequestParam CardColor color, Authentication authentication){

    Cliente autenticado = clienteService.buscarClientePorEmail(authentication.getName());
    Card card = new Card(type, color, (generateNumber(1,10000)+ " "+generateNumber(1,10000)+" "+generateNumber(1,10000)+" "+generateNumber(1,10000)),generateCvv(1,1000), LocalDate.now(), LocalDate.now().plusYears(5));

    return card;
  }

}
