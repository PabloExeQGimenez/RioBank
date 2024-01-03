package com.exeq.riobank.utils;

import com.exeq.riobank.repositories.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class CardUtils {
  @Autowired
  private static CardRepo cardRepo;

  public static String generateNumber(int min, int max) {

    long number;
    String numberCompleted;
    do {
      number = (int) ((Math.random() * (max - min)) + min);
      numberCompleted = String.format("%04d", number);
    } while (cardRepo.existsByNumber(numberCompleted));
    return numberCompleted;
  }

  public static String generateCvv(int min, int max) {
    long number;
    String numberCompleted;
    do {
      number = (int) ((Math.random() * (max - min)) + min);
      numberCompleted = String.format("%03d", number);
    } while (cardRepo.existsByCvv(numberCompleted));
    return numberCompleted;
  }
}
