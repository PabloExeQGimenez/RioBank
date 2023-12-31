package com.exeq.riobank;

import com.exeq.riobank.DTOs.ClienteLoanDTO;
import com.exeq.riobank.models.*;
import com.exeq.riobank.repositories.ClientLoanRepo;
import com.exeq.riobank.repositories.ClienteRepo;
import com.exeq.riobank.repositories.CuentaRepo;
import com.exeq.riobank.repositories.LoanRepo;
import com.exeq.riobank.service.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class RiobankApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(RiobankApplication.class, args);

	}

	@Bean
	public CommandLineRunner initData(ClientLoanRepo clientLoanRepo, LoanRepo loanRepo, ClientLoanService clientLoanService, LoanService loanService, TransactionService transactionService, ClienteService clienteService, CuentaService cuentaService, CuentaRepo cuentaRepo, ClienteRepo clienteRepo) {
		return args -> {

			Cliente clienteMelba = clienteService.insertarCliente("Melba", "Morel", "melba@gmail.com", passwordEncoder.encode("melba"));
			Cliente clienteJuana = clienteService.insertarCliente("Juana", "Pascal", "juana@gmail.com", passwordEncoder.encode("juana"));
			Cliente clienteJose = clienteService.insertarCliente("Jose", "Rodriguez", "jose@gmail.com", passwordEncoder.encode("jose"));
			Cliente clienteMilagros = clienteService.insertarCliente("Milagros", "Avellaneda", "milagros@gmail.com", passwordEncoder.encode("milagros"));

			LocalDate hoy = LocalDate.now();
			LocalDate diaSiguiente = hoy.plusDays(1);
			Cuenta cuentaMelba1 = cuentaService.insertarCuenta("VIN001", hoy, 5000.00);
			Cuenta cuentaMelba2 = cuentaService.insertarCuenta("VIN002", diaSiguiente, 7500.00);
			clienteMelba.agregarCuenta(cuentaMelba1);
			clienteMelba.agregarCuenta(cuentaMelba2);
			cuentaRepo.save(cuentaMelba1);
			cuentaRepo.save(cuentaMelba2);
			clienteRepo.save(clienteMelba);

			LocalDateTime ahora = LocalDateTime.now();
			Transaction transactionMelba = transactionService.createTransaction(TransactionType.CREDIT, 2000.00, "Prueba credit", ahora );

			cuentaMelba1.addTransaction(transactionMelba);
			transactionService.saveTransactions(transactionMelba);
			Transaction transactionMelba2 = transactionService.createTransaction(TransactionType.DEBIT, 3000.00, "Prueba debit", ahora );
			cuentaMelba1.addTransaction(transactionMelba2);
			transactionService.saveTransactions(transactionMelba2);

			Loan mortgageLoan = loanService.createLoan("Mortgage", 500000.00, List.of(12, 24, 36, 48, 60));
			Loan personalLoan = loanService.createLoan("Personal", 100000.00, List.of(6, 12, 24));
			Loan autoLoan = loanService.createLoan("Automotriz", 300000.00, List.of(6, 12, 24,36));

			ClientLoan melbaLoanMortgage = clientLoanService.createClientLoan(mortgageLoan, 400000.00, 60);
			ClientLoan melbaLoanPersonal = clientLoanService.createClientLoan(personalLoan, 50000.00, 12);
			mortgageLoan.addClientLoan(melbaLoanMortgage);
			personalLoan.addClientLoan(melbaLoanPersonal);
			clienteMelba.addClientLoan(melbaLoanMortgage);
			clienteMelba.addClientLoan(melbaLoanPersonal);
			loanRepo.save(mortgageLoan);
			loanRepo.save(personalLoan);
			clientLoanRepo.save(melbaLoanMortgage);
			clientLoanRepo.save(melbaLoanPersonal);
			clienteRepo.save(clienteMelba);

		};

	}

	}