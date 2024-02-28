package com.exeq.riobank.service;

import com.exeq.riobank.models.Transaction;
import com.exeq.riobank.models.TransactionType;

import java.time.LocalDateTime;

public interface TransactionService {

  Transaction createTransaction(TransactionType type, Double amount, String description, LocalDateTime date, Double balance);

  void saveTransactions(Transaction transactions);
}
