package com.exeq.riobank.mappers;

import com.exeq.riobank.DTOs.TransactionDTO;
import com.exeq.riobank.models.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

  @Mappings({
    @Mapping(source = "date", target="date", dateFormat = "yyyy-MM-dd HH-mm-ss")
  })
  TransactionDTO transformarATransactionDTO(Transaction transaction);
  List<TransactionDTO> transformarAListaTransactionDTO(List<Transaction> transactions);
}
