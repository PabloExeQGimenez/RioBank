package com.exeq.riobank.DTOs;


import com.exeq.riobank.models.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
  private Long id;
  private TransactionType type;
  private Double amount;
  private String description;
  private LocalDateTime date;
  private Double balance;
}
