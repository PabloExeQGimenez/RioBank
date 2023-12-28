package com.exeq.riobank.mappers;

import com.exeq.riobank.DTOs.ClienteLoanDTO;
import com.exeq.riobank.DTOs.ClienteDTO;
import com.exeq.riobank.DTOs.ClienteLoanDTO;
import com.exeq.riobank.models.ClientLoan;
import com.exeq.riobank.models.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientLoanMapper {
 @Mapping(target = "id", source = "id")
 @Mapping(target = "payments", source = "payments")
 @Mapping(target = "loanId", expression = "java(clientLoan.getLoan().getId())")
 @Mapping(target = "loanName", expression = "java(clientLoan.getLoan().getName())")
  ClienteLoanDTO transformarAClientLoanDTO(ClientLoan clientLoan);
  List<ClienteLoanDTO> transformarListaAClientLoanDTO(List<ClientLoan> loans);
}
