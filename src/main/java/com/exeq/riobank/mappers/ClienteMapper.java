package com.exeq.riobank.mappers;

import com.exeq.riobank.DTOs.CardDTO;
import com.exeq.riobank.DTOs.ClienteLoanDTO;
import com.exeq.riobank.DTOs.ClienteDTO;
import com.exeq.riobank.models.Card;
import com.exeq.riobank.models.ClientLoan;
import com.exeq.riobank.models.Cliente;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CardMapper.class,CuentaMapper.class,TransactionMapper.class, ClientLoanMapper.class}) //si usa otro mapper por relaciones
public interface ClienteMapper {
  ClienteDTO transformarAClienteDTO(Cliente cliente);
   List<ClienteDTO> transformarAListaClienteDTO(List<Cliente> clientes);
}
