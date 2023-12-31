package io.github.rafaelpino.api.resource;

import io.github.rafaelpino.api.exceptions.CreditCardNotFoundException;
import io.github.rafaelpino.api.exceptions.ResponseError;
import io.github.rafaelpino.application.dto.PaymentDTO;
import io.github.rafaelpino.domain.entities.CreditCard;
import io.github.rafaelpino.domain.services.PaymentService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Set;

@Path("/payments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {
    private PaymentService paymentService;
    private Validator validator;

    @Inject
    public PaymentResource(PaymentService paymentService, Validator validator) {
        this.paymentService = paymentService;
        this.validator = validator;
    }
    @GET
    public Response listAllPayments(){
        List<PaymentDTO> list = paymentService.ListPayment();
        return Response.ok(list.stream().toList()).build();
    }

    @POST
    @Transactional
    public Response createPayment(PaymentDTO paymentDTO){
        Set<ConstraintViolation<PaymentDTO>> violations = validator.validate(paymentDTO);
        if(!violations.isEmpty()){
            ResponseError responseError = ResponseError.createFromValidation(violations);
            return Response.status(400).entity(responseError).build();
        }
        CreditCard verification = paymentService.findIdCard(paymentDTO.getCard().getId());
        if(verification == null){
            CreditCardNotFoundException exception = new CreditCardNotFoundException("unable to find credit card");
            return Response.status(404).entity(exception).build();
        }
        PaymentDTO payment = paymentService.createPayment(paymentDTO);
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(payment).build();
    }
}
