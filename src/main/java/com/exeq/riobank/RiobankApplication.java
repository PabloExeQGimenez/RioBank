package com.exeq.riobank;

import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.repositories.ClienteRepo;
import com.exeq.riobank.service.ClienteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RiobankApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiobankApplication.class, args);

	}

	@Bean
	public CommandLineRunner initData(ClienteService clienteService) {
		return args -> {

			Cliente clienteMelba = clienteService.insertarCliente("Melba", "Morel", "melba@gmail.com");
			Cliente clienteJuana = clienteService.insertarCliente("Juana", "Pascal", "juana@gmail.com");
			Cliente clienteJose = clienteService.insertarCliente("Jose", "Rodriguez", "jose@gmail.com");
			Cliente clienteMilagros = clienteService.insertarCliente("Milagros", "Avellaneda", "milagros@gmail.com");

		};

	}
}