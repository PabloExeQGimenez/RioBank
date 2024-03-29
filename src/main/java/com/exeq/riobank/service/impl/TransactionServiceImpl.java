package com.exeq.riobank.service.impl;

import com.exeq.riobank.models.Cuenta;
import com.exeq.riobank.models.Transaction;
import com.exeq.riobank.models.TransactionType;
import com.exeq.riobank.repositories.TransactionRepo;
import com.exeq.riobank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {
  @Autowired
  private TransactionRepo transactionRepo;
  @Override
  public Transaction createTransaction(TransactionType type, Double amount, String description, LocalDateTime date, Double balance) {
    Transaction transaction = new Transaction();
    transaction.setType(type);
    transaction.setAmount(amount);
    transaction.setDescription(description);
    transaction.setDate(date);
    transaction.setBalance(balance);

    return transactionRepo.save(transaction);
  }

  @Override
  public void saveTransactions(Transaction transactions) {
    transactionRepo.save(transactions);
  }


}
