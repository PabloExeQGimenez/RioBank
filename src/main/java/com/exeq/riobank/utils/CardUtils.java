package com.exeq.riobank.utils;

import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;
@Component
public class CardUtils {
  public static String generateNumber(int min, int max) {
    Set<String> setCards = new HashSet<>();
    long number;
    String numberCompleted;
    do {
      number = (int) ((Math.random() * (max - min)) + min);
      String formattedNumber = String.format("%04d", number);
      numberCompleted = formattedNumber;
    } while (setCards.contains(numberCompleted));
    return numberCompleted;
  }

  public static String generateCvv(int min, int max) {
    Set<String> setCvvs = new HashSet<>();
    long number;
    String numberCompleted;
    do {
      number = (int) ((Math.random() * (max - min)) + min);
      String formattedNumber = String.format("%03d", number);
      numberCompleted = formattedNumber;
    } while (setCvvs.contains(numberCompleted));
    return numberCompleted;
  }
}