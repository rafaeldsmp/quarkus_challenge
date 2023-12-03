package io.github.rafaelpino.application.service;

import io.github.rafaelpino.application.dto.PaymentDTO;
import io.github.rafaelpino.domain.entities.Payment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PaymentMapper {
    @Inject
    private CardMapper cardMapper;
    public PaymentDTO mapToDTO(Payment payment){
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setDateTimePayment(payment.getPaymentDate());
        paymentDTO.setTransactionStatus(payment.getTransactionStatus());
        paymentDTO.setResponseMessage(payment.getResponseMessage());
        if(payment.getCreditCard() != null){
            paymentDTO.setCard(cardMapper.mapToDTO(payment.getCreditCard()));
        }
        return paymentDTO;
    }

    public Payment mapToEntity(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setId(paymentDTO.getId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getDateTimePayment());
        payment.setTransactionStatus(paymentDTO.getTransactionStatus());
        payment.setResponseMessage(paymentDTO.getResponseMessage());
        if (paymentDTO.getCard() != null){
            payment.setCreditCard(cardMapper.mapToEntity(paymentDTO.getCard()));
        }
        return payment;
    }
}
