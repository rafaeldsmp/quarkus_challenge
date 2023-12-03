package io.github.rafaelpino.domain.entities;

import io.github.rafaelpino.domain.enums.TransactionStatus;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_payment")
@Data
public class Payment extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;
    @Column(name = "response_message")
    private String responseMessage;
    @ManyToOne
    @JoinColumn(name = "creditcard_id")
    private CreditCard creditCard;

}
