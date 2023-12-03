package io.github.rafaelpino.domain.services;

import io.github.rafaelpino.application.dto.CreditCardDTO;
import io.github.rafaelpino.application.service.CardMapper;
import io.github.rafaelpino.domain.entities.CreditCard;
import io.github.rafaelpino.infrastructure.repositories.CreditCardRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CreditCardService {
    @Inject
    private CreditCardRepository creditCardRepository;
    @Inject
    private CardMapper cardMapper;
    public List<CreditCardDTO> findAll(){
        List <CreditCard> creditCards = creditCardRepository.listAll();
        return creditCards.stream()
                .map(cardMapper::mapToDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public CreditCardDTO createCard(CreditCardDTO creditCardDTO){
        Long idCreditCard =creditCardDTO.getId();
        CreditCard creditCard = cardMapper.mapToEntity(creditCardDTO);
        creditCard.persist();
        return cardMapper.mapToDTO(creditCard);
    }
}
