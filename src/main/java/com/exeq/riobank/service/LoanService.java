package com.exeq.riobank.service;

import com.exeq.riobank.models.Loan;

import java.util.List;

public interface LoanService {
Loan createLoan(String name, Double maxAmount, List<Integer> payments);

Loan saveLoan(Loan loan);

}
