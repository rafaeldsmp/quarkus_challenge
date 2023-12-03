package io.github.rafaelpino.domain.services;


import io.github.rafaelpino.application.dto.PaymentDTO;
import io.github.rafaelpino.application.service.PaymentMapper;
import io.github.rafaelpino.domain.entities.CreditCard;
import io.github.rafaelpino.domain.entities.Payment;
import io.github.rafaelpino.infrastructure.repositories.CreditCardRepository;
import io.github.rafaelpino.infrastructure.repositories.PaymentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PaymentService {

    @Inject
    private PaymentRepository paymentRepository;
    @Inject
    private CreditCardRepository creditCardRepository;
    @Inject
    private PaymentMapper paymentMapper;

    public List<PaymentDTO> ListPayment(){
        List<Payment> payments = paymentRepository.listAll();
        return payments.stream().map(paymentMapper::mapToDTO).collect(Collectors.toList());
    }
    public CreditCard findIdCard(Long id){
            return creditCardRepository.findById(id);
    }
    @Transactional
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = paymentMapper.mapToEntity(paymentDTO);
        payment.persist();
        return paymentMapper.mapToDTO(payment);
    }
}
