package io.github.rafaelpino.api.resource;

import io.github.rafaelpino.api.exceptions.ResponseError;
import io.github.rafaelpino.application.dto.CreditCardDTO;
import io.github.rafaelpino.domain.services.CreditCardService;

import io.micrometer.core.annotation.Counted;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Set;

@Path("/creditcards")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CreditCardResource {
    private CreditCardService creditCardService;
    private Validator validator;

    @Inject
    public CreditCardResource(CreditCardService creditCardService, Validator validator){
        this.creditCardService = creditCardService;
        this.validator = validator;
    }

    @GET
    @Counted(value = "List All Credit Cards", description = "Number of times that the method listAllCreditCards was invoke")
    public Response listAllCreditCards(){
        List<CreditCardDTO> query = creditCardService.findAll();
        return Response.ok(query.stream().toList()).build();
    }

    @POST
    @Transactional
    public Response createCard(CreditCardDTO creditCardDTO){
        Set<ConstraintViolation<CreditCardDTO>> violations = validator.validate(creditCardDTO);
        if(!violations.isEmpty()){
            ResponseError responseError = ResponseError.createFromValidation(violations);
            return Response.status(400).entity(responseError).build();
        }
        CreditCardDTO card = creditCardService.createCard(creditCardDTO);
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(card).build();
    }
}
