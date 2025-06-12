package com.microservices.cards;

import com.microservices.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Cards, Long> {
}
