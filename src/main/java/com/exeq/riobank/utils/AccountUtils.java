package com.exeq.riobank.utils;

import com.exeq.riobank.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountUtils {
  @Autowired
  private CuentaService cuentaService;
  public static String generateRandomNumber() {
    return "VIN-" + (int) ((Math.random() * (999999 - 000000)));
  }
}
