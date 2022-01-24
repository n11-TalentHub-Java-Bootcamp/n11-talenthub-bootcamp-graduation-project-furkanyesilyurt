package com.furkanyesilyurt.creditapplicationsystem.excepiton.givencredit;

public class GivenCreditNotFoundException extends RuntimeException{
    public GivenCreditNotFoundException(String message) {
        super(message);
    }
}
