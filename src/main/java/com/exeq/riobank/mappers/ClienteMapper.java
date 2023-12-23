package com.exeq.riobank.mappers;

import com.exeq.riobank.DTOs.ClienteDTO;
import com.exeq.riobank.models.Cliente;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CuentaMapper.class}) //si usa otro mapper por relaciones
public interface ClienteMapper {

  @Mappings({
      @Mapping(source = "apellido", target = "apellido"),
     @Mapping(source = "email", target = "correo"),
  })

  ClienteDTO transformarAClienteDTO(Cliente cliente);

  List<ClienteDTO> transformarAListaClienteDTO(List<Cliente> clientes);

}
