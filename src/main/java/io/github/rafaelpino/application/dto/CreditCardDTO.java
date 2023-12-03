package io.github.rafaelpino.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreditCardDTO {
    private Long id;
    @NotNull(message = "CreditCard Number is required")
    private Integer cardNumber;
    @NotBlank(message = "CreditCard Holder Name is required")
    private String cardHolderName;//
    @NotNull(message = "Security Code is required")
    private Integer securityCode;
}
