package io.github.rafaelpino.infrastructure.repositories;

import io.github.rafaelpino.domain.entities.CreditCard;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class CreditCardRepository implements PanacheRepository<CreditCard> {
}
