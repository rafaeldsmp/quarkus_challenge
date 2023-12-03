package io.github.rafaelpino.api.exceptions;

public class CreditCardNotFoundException extends RuntimeException{
    public CreditCardNotFoundException(String message){
        super(message);
    }
}
