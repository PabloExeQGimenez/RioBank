package com.exeq.riobank.mappers;

import com.exeq.riobank.DTOs.ClienteDTO;
import com.exeq.riobank.models.Cliente;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {

  @Mappings({
      @Mapping(source = "id", target = "id"),
      @Mapping(source = "nombre", target = "nombre"),
      @Mapping(source = "apellido", target = "apellido"),
  })

  ClienteDTO transformarAClienteDTO(Cliente cliente);

  List<ClienteDTO> transformarAListaClienteDTO(List<Cliente> clientes);

}
