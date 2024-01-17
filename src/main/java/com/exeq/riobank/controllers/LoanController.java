package com.exeq.riobank.controllers;

import com.exeq.riobank.DTOs.LoanApplicationDTO;
import com.exeq.riobank.DTOs.LoanDTO;
import com.exeq.riobank.mappers.LoanMapper;
import com.exeq.riobank.models.*;
import com.exeq.riobank.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @Autowired
    private LoanMapper loanMapper;

    @GetMapping("/loans")
    public List<LoanDTO> getAllLoans(){
        return loanMapper.transformarAListaLoanDTO(loanService.findAllLoans());
    }



}
