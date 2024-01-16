package com.exeq.riobank.controllers;

import com.exeq.riobank.DTOs.LoanApplicationDTO;
import com.exeq.riobank.models.*;
import com.exeq.riobank.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private ClientLoanService clientLoanService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/requestLoan")
    public ResponseEntity<Object> requestLoan(Authentication authentication,
                                              @RequestBody LoanApplicationDTO loanApplicationDTO){

        Cliente autenticado = clienteService.buscarClientePorEmail(authentication.getName());
        Loan loan = loanService.findLoanById(loanApplicationDTO.getIdLoan());
        Cuenta account = cuentaService.findByNumero(loanApplicationDTO.getDestinationAccount());
        if (autenticado == null) {
            return new ResponseEntity<>("Unknow client " + authentication.getName(), HttpStatus.UNAUTHORIZED);
        }
        if (loanApplicationDTO.getIdLoan() == 0) {
            return new ResponseEntity<>("Loan is required", HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getAmount() == 0) {
            return new ResponseEntity<>("Amount is required", HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getAmount() <= 0) {
            return new ResponseEntity<>("The amount must be greater than zero", HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getPayments() == 0) {
            return new ResponseEntity<>("Number of payments is required", HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getDestinationAccount().isBlank()) {
            return new ResponseEntity<>("Destination account is required", HttpStatus.FORBIDDEN);
        }
        if (loan == null) {
            return new ResponseEntity<>("Loan doesn´t exist", HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getAmount() > loan.getMaxAmount()) {
            return new ResponseEntity<>("The request exceeds the available amount", HttpStatus.FORBIDDEN);
        }
        if (!loan.getPayments().contains(loanApplicationDTO.getPayments())) {
            return new ResponseEntity<>("Amount of payments is incorrect", HttpStatus.FORBIDDEN);
        }
        if (account == null) {
            return new ResponseEntity<>("Please enter a valid destination account", HttpStatus.FORBIDDEN);
        }
        if (!autenticado.getCuentas().contains(account)) {
            return new ResponseEntity<>("Enter an account you belong to", HttpStatus.FORBIDDEN);
        }

        ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount() * 1.20, loanApplicationDTO.getPayments());
        autenticado.addClientLoan(clientLoan);
        clientLoanService.saveClientLoan(clientLoan);
        Transaction transactionCredit = transactionService.createTransaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(),
                loan.getName() + " Loan approved", LocalDateTime.now());
        transactionService.saveTransactions(transactionCredit);
        account.addTransaction(transactionCredit);
        account.setBalance(account.getBalance() + loanApplicationDTO.getAmount());
        cuentaService.saveAccount(account);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
