package com.furkanyesilyurt.creditapplicationsystem.excepiton.customer;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
