package com.exeq.riobank.controllers;

import com.exeq.riobank.DTOs.ClienteLoanDTO;
import com.exeq.riobank.mappers.ClientLoanMapper;
import com.exeq.riobank.models.*;
import com.exeq.riobank.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientLoanController {

    @Autowired
    private ClientLoanService clientLoanService;
    @Autowired
    private ClientLoanMapper clientLoanMapper;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/clientloans")
    public List<ClienteLoanDTO> getAllClientLoans(){
        return clientLoanMapper.transformarListaAClientLoanDTO(clientLoanService.getAllClientLoans());
    }

    @PostMapping("/clientloans")
    public ResponseEntity<Object> createClientLoan(
                                       @RequestParam Double amount,
                                       @RequestParam Integer payments,
                                       @RequestParam String destinationAccount,
                                       @RequestParam Long loanId,
                                       Authentication authentication){

        Cliente autenticado = clienteService.buscarClientePorEmail(authentication.getName());
        Loan loan = loanService.findLoanById(loanId);
        Cuenta account = cuentaService.findByNumero(destinationAccount);
        ClientLoan clientLoan = clientLoanService.createClientLoan(loan, amount, payments);
        autenticado.addClientLoan(clientLoan);
        Transaction transactionCredit = transactionService.createTransaction(TransactionType.CREDIT, amount, "Credit " + loan.getName() + " approved", LocalDateTime.now());
        account.addTransaction(transactionCredit);
        account.setBalance(account.getBalance() + amount);
        cuentaService.saveAccount(account);
        clientLoanService.saveClientLoan(clientLoan);
        clienteService.saveClient(autenticado);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
