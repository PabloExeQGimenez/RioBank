package com.exeq.riobank.mappers;

import com.exeq.riobank.DTOs.CardDTO;
import com.exeq.riobank.models.Card;
import org.mapstruct.*;
import java.util.List;
@Mapper(componentModel = "spring")
public interface CardMapper {

  CardDTO transformarACardDTO(Card card);
  List<CardDTO> transformarAListaCardsDTO(List<Card> cards);
}
