package com.exeq.riobank.mappers;

import com.exeq.riobank.DTOs.ClienteLoanDTO;
import com.exeq.riobank.DTOs.ClienteDTO;
import com.exeq.riobank.models.ClientLoan;
import com.exeq.riobank.models.Cliente;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CuentaMapper.class,TransactionMapper.class, ClientLoanMapper.class}) //si usa otro mapper por relaciones
public interface ClienteMapper {

  @Mapping(source = "email", target = "correo")
  ClienteDTO transformarAClienteDTO(Cliente cliente);

  List<ClienteDTO> transformarAListaClienteDTO(List<Cliente> clientes);
}
