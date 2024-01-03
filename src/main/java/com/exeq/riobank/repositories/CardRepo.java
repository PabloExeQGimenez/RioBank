package com.exeq.riobank.repositories;

import com.exeq.riobank.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface CardRepo extends JpaRepository<Card, Long> {
  boolean existsByNumber(String cardNumber);
  boolean existsByCvv(String cardCvv);
}
