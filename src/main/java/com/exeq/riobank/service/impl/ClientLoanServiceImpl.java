package com.exeq.riobank.service.impl;

import com.exeq.riobank.models.ClientLoan;
import com.exeq.riobank.models.Loan;
import com.exeq.riobank.repositories.ClientLoanRepo;
import com.exeq.riobank.service.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientLoanServiceImpl implements ClientLoanService {
  @Autowired
  private ClientLoanRepo clientLoanRepo;
  @Override
  public ClientLoan createClientLoan(Loan loan, Double amount, Integer payments) {
    ClientLoan clientLoan = new ClientLoan();
    clientLoan.setLoan(loan);
    clientLoan.setAmount(amount);
    clientLoan.setPayments(payments);
    return clientLoanRepo.save(clientLoan);
  }

  @Override
  public void saveClientLoan(ClientLoan clientLoan) {
    clientLoanRepo.save(clientLoan);
  }

  @Override
  public List<ClientLoan> getAllClientLoans() {
    return clientLoanRepo.findAll();
  }
}
