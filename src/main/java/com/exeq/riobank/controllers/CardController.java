package com.exeq.riobank.controllers;
import com.exeq.riobank.DTOs.CardDTO;
import com.exeq.riobank.models.CardColor;
import com.exeq.riobank.models.CardType;
import com.exeq.riobank.models.Card;
import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.repositories.CardRepo;
import com.exeq.riobank.service.CardService;
import com.exeq.riobank.service.ClienteService;
import com.exeq.riobank.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
  @Autowired
  private CardRepo cardRepo;

  @PostMapping("/clientes/current/cards")
  public ResponseEntity<Object> createCard(@RequestParam String type, @RequestParam String color, Authentication authentication) {
    Cliente cliente = clienteService.buscarClientePorEmail(authentication.getName());
    Card card = new Card((cliente.getNombre() + " " + cliente.getApellido()), CardType.valueOf(type), CardColor.valueOf(color), (generateNumber(1, 10000) + " " + generateNumber(1, 10000) + " " + generateNumber(1, 10000) + " " + generateNumber(1, 10000)), generateCvv(1, 1000), LocalDate.now(), LocalDate.now().plusYears(5));
    cliente.addCard(card);
    cardService.saveCard(card);
    clienteService.saveClient(cliente);
    return new ResponseEntity<>("Card created!", HttpStatus.CREATED);
  }

  @PatchMapping("/clientes/current/cards")
  public CardDTO deleteCard(@RequestParam Long id, Authentication authentication) {
    Cliente cliente = clienteService.buscarClientePorEmail(authentication.getName());

    Card card = c

    }
  }
}
