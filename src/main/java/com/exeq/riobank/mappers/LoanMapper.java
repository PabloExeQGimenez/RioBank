package com.exeq.riobank.mappers;

import com.exeq.riobank.DTOs.LoanDTO;
import com.exeq.riobank.models.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(target = "id", source = "id")
    LoanDTO transformarALoanDTO(Loan loan);
    List<LoanDTO> transformarAListaLoanDTO(List<Loan> loans);
}
