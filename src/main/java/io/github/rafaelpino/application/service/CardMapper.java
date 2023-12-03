package io.github.rafaelpino.application.service;

import io.github.rafaelpino.application.dto.CreditCardDTO;
import io.github.rafaelpino.domain.entities.CreditCard;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CardMapper {
    public CreditCardDTO mapToDTO(CreditCard creditCard){
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setId(creditCard.getId());
        creditCardDTO.setCardNumber(creditCard.getCardNumber());
        creditCardDTO.setCardHolderName(creditCard.getCardHolderName());
        creditCardDTO.setSecurityCode(creditCard.getSecurityCode());
        return creditCardDTO;
    }
    public CreditCard mapToEntity(CreditCardDTO creditCardDTO){
        CreditCard creditCard = new CreditCard();
        creditCard.setId(creditCardDTO.getId());
        creditCard.setCardNumber(creditCardDTO.getCardNumber());
        creditCard.setCardHolderName(creditCardDTO.getCardHolderName());
        creditCard.setSecurityCode(creditCardDTO.getSecurityCode());
        return creditCard;
    }

}
