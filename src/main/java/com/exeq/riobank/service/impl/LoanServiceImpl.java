package com.exeq.riobank.service.impl;

import com.exeq.riobank.models.Loan;
import com.exeq.riobank.repositories.LoanRepo;
import com.exeq.riobank.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
  @Autowired
  private LoanRepo loanRepo;
  @Override
  public Loan createLoan(String name, Double maxAmount, List<Integer> payments) {
    Loan loan = new Loan();
    loan.setName(name);
    loan.setMaxAmount(maxAmount);
    loan.setPayments(payments);
    return loanRepo.save(loan);
  }

  @Override
  public Loan saveLoan(Loan loan) {
    return loanRepo.save(loan);

  }

  @Override
  public Loan findLoanById(Long idLoan) {
    return loanRepo.findById(idLoan).orElse(null);
  }

  @Override
  public List<Loan> findAllLoans() {
    return loanRepo.findAll();
  }


}
