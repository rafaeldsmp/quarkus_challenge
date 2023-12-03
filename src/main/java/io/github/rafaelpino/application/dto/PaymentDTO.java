package io.github.rafaelpino.application.dto;

import io.github.rafaelpino.domain.enums.TransactionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Long id;
    @NotNull(message = "amount is required")
    private Double amount;
    @NotNull(message = "Date Time Payment is required")
    private LocalDateTime dateTimePayment;
    @NotNull(message = "Transaction Status is required")
    private TransactionStatus transactionStatus;
    @NotBlank(message = "Response Message is required")
    private String responseMessage;
    private CreditCardDTO card;

}
