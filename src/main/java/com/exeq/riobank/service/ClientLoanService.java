package com.exeq.riobank.service;

import com.exeq.riobank.models.ClientLoan;
import com.exeq.riobank.models.Loan;

import java.util.List;

public interface ClientLoanService {

  ClientLoan createClientLoan(Loan loan, Double amount, Integer payments);
  void saveClientLoan(ClientLoan clientLoan);

  List<ClientLoan> getAllClientLoans();
}
