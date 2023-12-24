package com.exeq.riobank.mappers;

import com.exeq.riobank.DTOs.CuentaDTO;
import com.exeq.riobank.models.Cuenta;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {TransactionMapper.class})
public interface CuentaMapper {

  CuentaDTO transformarACuentaDTO(Cuenta cuenta);
  List<CuentaDTO> transformarAListaCuentaDTO(List<Cuenta> cuentas);

}
