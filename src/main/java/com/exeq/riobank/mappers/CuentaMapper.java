package com.exeq.riobank.mappers;

import com.exeq.riobank.DTOs.CuentaDTO;
import com.exeq.riobank.models.Cuenta;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

  CuentaDTO transformarACuentaDTO(Cuenta cuenta);

  List<CuentaDTO> transformarAListaCuentaDTO(List<Cuenta> cuentas);

}
