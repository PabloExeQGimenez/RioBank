package com.exeq.riobank.controllers;

import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.models.Cuenta;
import com.exeq.riobank.models.Transaction;
import com.exeq.riobank.models.TransactionType;
import com.exeq.riobank.service.ClienteService;
import com.exeq.riobank.service.CuentaService;
import com.exeq.riobank.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class TransactionsController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private CuentaService cuentaService;
    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> newTransactions(Authentication authentication,
                                                  @RequestParam Double amount,
                                                  @RequestParam String description,
                                                  @RequestParam String originNumber,
                                                  @RequestParam String destinationNumber) {
        Cliente autenticado = clienteService.buscarClientePorEmail(authentication.getName());
        Cuenta accountDebit = cuentaService.findByNumero(originNumber);
        Cuenta accountCredit = cuentaService.findByNumero(destinationNumber);

        if (autenticado == null) {
            throw new UsernameNotFoundException("Unknow client " + authentication.getName());
        }

        if (accountDebit.getCliente() != autenticado) {
            return new ResponseEntity<>("The account does not belong to you",
                    HttpStatus.FORBIDDEN);
        }
        if (accountDebit == null) {
            return new ResponseEntity<>("The account doesn´t exist", HttpStatus.FORBIDDEN);
        }
        if (accountCredit == null) {
            return new ResponseEntity<>("The destination account doesn´t exist", HttpStatus.FORBIDDEN);
        }
        if (accountDebit.getBalance() < amount) {
            return new ResponseEntity<>("Insufficient balance", HttpStatus.FORBIDDEN);
        }
        if (accountDebit.getNumero() == accountCredit.getNumero()) {
            return new ResponseEntity<>("You cannot transfer to the same account",
                    HttpStatus.FORBIDDEN);
        } else {
            Transaction transactionCredit = transactionService.createTransaction(TransactionType.CREDIT, amount, description, LocalDateTime.now(), (accountCredit.getBalance() + amount));
            Transaction transactionDebit = transactionService.createTransaction(TransactionType.DEBIT, -amount, description, LocalDateTime.now(), (accountDebit.getBalance() - amount));
            transactionService.saveTransactions(transactionCredit);
            transactionService.saveTransactions(transactionDebit);
            accountDebit.addTransaction(transactionDebit);
            accountCredit.addTransaction(transactionCredit);
            accountDebit.setBalance(accountDebit.getBalance() - amount);
            accountCredit.setBalance(accountCredit.getBalance() + amount);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
