package com.exeq.riobank.mappers;

import com.exeq.riobank.DTOs.TransactionDTO;
import com.exeq.riobank.models.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
  TransactionDTO transformarATransactionDTO(Transaction transaction);
  List<TransactionDTO> transformarAListaTransactionDTO(List<Transaction> transactions);
}
