package io.github.rafaelpino.domain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tb_card")
@Data
public class CreditCard extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "card_number")
    private Integer cardNumber;
    @Column(name = "card_holder_name")
    private String cardHolderName;
    @Column(name = "security_code")
    private Integer securityCode;
    @OneToMany(mappedBy = "creditCard", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();
}
