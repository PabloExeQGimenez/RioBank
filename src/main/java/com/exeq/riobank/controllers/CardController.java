package com.exeq.riobank.controllers;

import com.exeq.riobank.models.CardColor;
import com.exeq.riobank.models.CardType;
import com.exeq.riobank.models.Card;

import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.service.CardService;
import com.exeq.riobank.service.ClienteService;
import com.exeq.riobank.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Object> createCard(@RequestParam String type, @RequestParam String color, Authentication authentication){

    Cliente autenticado = clienteService.buscarClientePorEmail(authentication.getName());
    Card card = new Card((autenticado.getNombre()+ " " + autenticado.getApellido() ), CardType.valueOf(type), CardColor.valueOf(color),"9238 8928 9823 7879","345" , LocalDate.now(), LocalDate.now().plusYears(5));

/*
    Card card = new Card(type, color, (generateNumber(1,10000)+ " "+generateNumber(1,10000)+" "+generateNumber(1,10000)+" "+generateNumber(1,10000)),generateCvv(1,1000), LocalDate.now(), LocalDate.now().plusYears(5));
*/

    cardService.saveCard(card);
    autenticado.addCard(card);
    clienteService.saveClient(autenticado);
    return new ResponseEntity<>("Card created!", HttpStatus.CREATED);
  }

}
