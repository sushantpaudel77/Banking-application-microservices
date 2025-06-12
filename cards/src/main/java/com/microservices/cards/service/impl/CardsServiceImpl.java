package com.microservices.cards.service.impl;

import com.microservices.cards.constants.CardsConstants;
import com.microservices.cards.dto.CardsDto;
import com.microservices.cards.entity.Cards;
import com.microservices.cards.exception.CardAlreadyExistsException;
import com.microservices.cards.exception.ResourceNotFoundException;
import com.microservices.cards.mapper.CardsMapper;
import com.microservices.cards.repository.CardRepository;
import com.microservices.cards.service.CardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardsServiceImpl implements CardsService {

    private final CardRepository cardRepository;
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final String MOBILE_NUMBER = "mobileNumber";

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber: " + mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCards = new Cards();
        newCards.setCardNumber(Long.toString(generateCardNumber()));
        newCards.setMobileNumber(mobileNumber);
        newCards.setCardType(CardsConstants.CREDIT_CARD);
        newCards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCards.setAmountUsed(0);
        newCards.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCards;
    }

    private long generateCardNumber() {
        long min = 10000000000L;
        long max = 99999999999L;
        return min + Math.abs(secureRandom.nextLong() % (max - min + 1));
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", MOBILE_NUMBER, mobileNumber));
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardRepository.findByMobileNumber(cardsDto.getMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", MOBILE_NUMBER, cardsDto.getMobileNumber()));
        CardsMapper.mapToCards(cardsDto, cards);
        cardRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", MOBILE_NUMBER, mobileNumber));
        cardRepository.deleteById(cards.getCardId());
        return true;
    }
}
