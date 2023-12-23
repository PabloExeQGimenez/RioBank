package com.exeq.riobank;

import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.models.Cuenta;
import com.exeq.riobank.repositories.ClienteRepo;
import com.exeq.riobank.repositories.CuentaRepo;
import com.exeq.riobank.service.ClienteService;
import com.exeq.riobank.service.CuentaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class RiobankApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiobankApplication.class, args);

	}

	@Bean
	public CommandLineRunner initData(ClienteService clienteService, CuentaService cuentaService, CuentaRepo cuentaRepo, ClienteRepo clienteRepo) {
		return args -> {

			Cliente clienteMelba = clienteService.insertarCliente("Melba", "Morel", "melba@gmail.com");
			Cliente clienteJuana = clienteService.insertarCliente("Juana", "Pascal", "juana@gmail.com");
			Cliente clienteJose = clienteService.insertarCliente("Jose", "Rodriguez", "jose@gmail.com");
			Cliente clienteMilagros = clienteService.insertarCliente("Milagros", "Avellaneda", "milagros@gmail.com");

			LocalDate hoy = LocalDate.now();
			LocalDate diaSiguiente = hoy.plusDays(1);
			Cuenta cuentaMelba1 = cuentaService.insertarCuenta("VIN001", hoy, 5000.00);
			Cuenta cuentaMelba2 = cuentaService.insertarCuenta("VIN002", diaSiguiente, 7500.00);


			clienteMelba.agregarCuenta(cuentaMelba1);
			clienteMelba.agregarCuenta(cuentaMelba2);


			cuentaRepo.save(cuentaMelba1);
			cuentaRepo.save(cuentaMelba2);
			clienteRepo.save(clienteMelba);

		};

	}
}