package com.exeq.riobank;

import com.exeq.riobank.DTOs.ClienteLoanDTO;
import com.exeq.riobank.models.*;
import com.exeq.riobank.repositories.ClientLoanRepo;
import com.exeq.riobank.repositories.ClienteRepo;
import com.exeq.riobank.repositories.CuentaRepo;
import com.exeq.riobank.repositories.LoanRepo;
import com.exeq.riobank.service.*;
import com.exeq.riobank.utils.AccountUtils;
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

import static com.exeq.riobank.utils.AccountUtils.generateRandomNumber;

@SpringBootApplication
public class RiobankApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(RiobankApplication.class, args);

	}

	@Bean
	public CommandLineRunner initData(CardService cardService, ClientLoanRepo clientLoanRepo, LoanRepo loanRepo, ClientLoanService clientLoanService, LoanService loanService, TransactionService transactionService, ClienteService clienteService, CuentaService cuentaService, CuentaRepo cuentaRepo, ClienteRepo clienteRepo) {
		return args -> {

			Cliente clienteEsmeralda = clienteService.insertarCliente("Esmeralda", "Oreiro", "esmeralda@gmail.com", passwordEncoder.encode("esmeralda"));
			Cliente clienteJuana = clienteService.insertarCliente("Juana", "Pascal", "juana@gmail.com", passwordEncoder.encode("juana"));
			Cliente clienteJose = clienteService.insertarCliente("Jose", "Rodriguez", "jose@gmail.com", passwordEncoder.encode("jose"));
			Cliente admin = clienteService.insertarCliente("admin", "admin", "admin@admin.com", passwordEncoder.encode("admin"));

			LocalDate hoy = LocalDate.now();
			LocalDate diaSiguiente = hoy.plusDays(1);

			Cuenta cuentaEsmeralda = cuentaService.insertarCuenta(generateRandomNumber(), hoy, 5000.00, CuentaTipo.SAVINGS);
			Cuenta cuentaEsmeralda2 = cuentaService.insertarCuenta(generateRandomNumber(), diaSiguiente, 9500.00, CuentaTipo.SAVINGS);
			clienteEsmeralda.agregarCuenta(cuentaEsmeralda);
			clienteEsmeralda.agregarCuenta(cuentaEsmeralda2);
			cuentaRepo.save(cuentaEsmeralda);
			cuentaRepo.save(cuentaEsmeralda2);
			clienteRepo.save(clienteEsmeralda);

			Cuenta cuentaJuana = cuentaService.insertarCuenta(generateRandomNumber(), hoy, 11500.00, CuentaTipo.SAVINGS);
			clienteJuana.agregarCuenta(cuentaJuana);
			cuentaRepo.save(cuentaJuana);
			clienteRepo.save(clienteJuana);

			Cuenta cuentaJose = cuentaService.insertarCuenta(generateRandomNumber(), hoy, 15000.00, CuentaTipo.SAVINGS);
			clienteJose.agregarCuenta(cuentaJose);
			cuentaRepo.save(cuentaJose);
			clienteRepo.save(clienteJose);


			LocalDateTime ahora = LocalDateTime.now();
			Transaction transactionEsmeralda = transactionService.createTransaction(TransactionType.CREDIT, 2000.00, "Prueba credit", ahora, 7000.00);
			cuentaEsmeralda.addTransaction(transactionEsmeralda);
			transactionService.saveTransactions(transactionEsmeralda);
			Transaction transactionMelba2 = transactionService.createTransaction(TransactionType.DEBIT, 3000.00, "Prueba debit", ahora, 4000.00 );
			cuentaEsmeralda.addTransaction(transactionMelba2);
			transactionService.saveTransactions(transactionMelba2);

			Transaction transactionJuana = transactionService.createTransaction(TransactionType.CREDIT, 3000.00, "Prueba credit", ahora, 14500.00);
			cuentaJuana.addTransaction(transactionJuana);
			transactionService.saveTransactions(transactionJuana);

			Transaction transactionJose = transactionService.createTransaction(TransactionType.CREDIT, 5000.00, "Prueba credit", ahora, 20000.00);
			cuentaJose.addTransaction(transactionJose);
			transactionService.saveTransactions(transactionJose);


			Loan mortgageLoan = loanService.createLoan("Mortgage", 500000.00, List.of(12, 24, 36, 48, 60));
			Loan personalLoan = loanService.createLoan("Personal", 100000.00, List.of(6, 12, 24));
			Loan autoLoan = loanService.createLoan("Automotriz", 300000.00, List.of(6, 12, 24,36));

			ClientLoan esmeraldaLoanMortgage = clientLoanService.createClientLoan(mortgageLoan, 4000.00, 60);
			ClientLoan esmeraldaLoanPersonal = clientLoanService.createClientLoan(personalLoan, 5000.00, 12);
			mortgageLoan.addClientLoan(esmeraldaLoanMortgage);
			personalLoan.addClientLoan(esmeraldaLoanPersonal);
			clienteEsmeralda.addClientLoan(esmeraldaLoanMortgage);
			clienteEsmeralda.addClientLoan(esmeraldaLoanPersonal);
			loanRepo.save(mortgageLoan);
			loanRepo.save(personalLoan);
			clientLoanRepo.save(esmeraldaLoanMortgage);
			clientLoanRepo.save(esmeraldaLoanPersonal);
			clienteRepo.save(clienteEsmeralda);

			ClientLoan juanaLoanAuto = clientLoanService.createClientLoan(autoLoan, 7000.00, 24);
			autoLoan.addClientLoan(juanaLoanAuto);
			clienteJuana.addClientLoan(juanaLoanAuto);
			loanRepo.save(autoLoan);
			clientLoanRepo.save(juanaLoanAuto);
			clienteRepo.save(clienteJuana);

			ClientLoan joseLoanMortgage = clientLoanService.createClientLoan(mortgageLoan, 9000.00, 60);
			mortgageLoan.addClientLoan(joseLoanMortgage);
			clienteJose.addClientLoan(joseLoanMortgage);
			loanRepo.save(mortgageLoan);
			clientLoanRepo.save(joseLoanMortgage);
			clienteRepo.save(clienteJose);



			Card cardEsmeralda = new Card(
					(clienteEsmeralda.getNombre() + " " + clienteEsmeralda.getApellido()),
					CardType.DEBIT, CardColor.TITANIUM,
					"9238 8928 9823 7879", "345",
					LocalDate.now().plusYears(-1),LocalDate.now()
			);
			clienteEsmeralda.addCard(cardEsmeralda);
			cardService.saveCard(cardEsmeralda);
			clienteService.saveClient(clienteEsmeralda);

			Card cardJuana = new Card(
					(clienteJuana.getNombre() + " " + clienteJuana.getApellido()),
					CardType.CREDIT, CardColor.SILVER,
					"5698 7849 1532 6492", "345",
					LocalDate.now().plusYears(5),LocalDate.now()
			);
			clienteJuana.addCard(cardJuana);
			cardService.saveCard(cardJuana);
			clienteService.saveClient(clienteJuana);

			Card cardJose = new Card(
					(clienteJose.getNombre() + " " + clienteJose.getApellido()),
					CardType.DEBIT, CardColor.GOLD,
					"2668 4859 1236 9548", "345",
					LocalDate.now().plusYears(5),LocalDate.now()
			);
			clienteJose.addCard(cardJose);
			cardService.saveCard(cardJose);
			clienteService.saveClient(clienteJose);

		};

		};

	}

