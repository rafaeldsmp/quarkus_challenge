package io.github.rafaelpino.api.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.ws.rs.core.Response;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ResponseError {
    private String message;
    private Collection<FieldError> errors;

    public ResponseError(String message, Collection<FieldError> errors) {
        this.message = message;
        this.errors = errors;
    }

    public static <T> ResponseError createFromValidation(Set<ConstraintViolation<T> >violations){
        List<FieldError> errors = violations
                .stream()
                .map(cv -> new FieldError(cv.getPropertyPath().toString(), cv.getMessage()))
                .collect(Collectors.toList());
        String message = "Validation Error";
        ResponseError responseError = new ResponseError(message, errors);
        return responseError;
    }
}
