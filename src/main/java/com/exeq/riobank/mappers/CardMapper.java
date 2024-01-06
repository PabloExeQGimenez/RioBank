package com.exeq.riobank.mappers;

import com.exeq.riobank.DTOs.CardDTO;
import com.exeq.riobank.models.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardMapper {
  CardDTO transformarACardDTO(Card card);
  List<CardDTO> transformarAListaCardDTO(List<Card> cards);
}
