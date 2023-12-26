package com.exeq.riobank.service;

import com.exeq.riobank.models.ClientLoan;
import com.exeq.riobank.models.Loan;

public interface ClientLoanService {

  ClientLoan createClientLoan(Loan loan, Double amount, Integer payments);
}
