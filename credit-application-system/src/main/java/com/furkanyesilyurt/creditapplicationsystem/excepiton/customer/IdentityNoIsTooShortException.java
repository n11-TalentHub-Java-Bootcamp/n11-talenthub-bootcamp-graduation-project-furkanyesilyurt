package com.furkanyesilyurt.creditapplicationsystem.excepiton.customer;

public class IdentityNoIsTooShortException extends RuntimeException{
    public IdentityNoIsTooShortException(String message) {
        super(message);
    }
}
