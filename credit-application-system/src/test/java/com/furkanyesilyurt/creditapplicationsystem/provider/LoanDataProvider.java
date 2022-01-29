package com.furkanyesilyurt.creditapplicationsystem.provider;

import com.furkanyesilyurt.creditapplicationsystem.entity.CreditScore;
import com.furkanyesilyurt.creditapplicationsystem.entity.Customer;
import com.furkanyesilyurt.creditapplicationsystem.service.entityservice.CreditScoreEntityService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LoanDataProvider {

    public static CreditScoreEntityService creditScoreEntityService;

    public static Customer getCustomer1() throws ParseException {
        Customer customer1 = Customer.builder()
                .id(2000L)
                .identityNo("12345678930")
                .firstname("firtname01")
                .lastname("lastname01")
                .monthlyIncome(BigDecimal.valueOf(5000.0))
                .phone("5381234501")
                .birthday(new SimpleDateFormat("yyyy-MM-dd").parse("1998-11-01"))
                .guarantee(BigDecimal.valueOf(10000.0))
                .build();
        return customer1;
    }

    public static Customer getCustomer2() throws ParseException {
        Customer customer2 = Customer.builder()
                .id(2000L)
                .identityNo("10735264945")
                .firstname("firtname01")
                .lastname("lastname01")
                .monthlyIncome(BigDecimal.valueOf(3500.0))
                .phone("5381234501")
                .birthday(new SimpleDateFormat("yyyy-MM-dd").parse("1998-11-01"))
                .guarantee(BigDecimal.valueOf(10000.0))
                .build();
        return customer2;
    }

    public static Customer getCustomer3() throws ParseException {
        Customer customer3 = Customer.builder()
                .id(2000L)
                .identityNo("10735264946")
                .firstname("firtname01")
                .lastname("lastname01")
                .monthlyIncome(BigDecimal.valueOf(7500.0))
                .phone("5381234501")
                .birthday(new SimpleDateFormat("yyyy-MM-dd").parse("1998-11-01"))
                .guarantee(BigDecimal.valueOf(10000.0))
                .build();
        return customer3;
    }

    public static Customer getCustomer4() throws ParseException {
        Customer customer4 = Customer.builder()
                .id(2000L)
                .identityNo("10735264947")
                .firstname("firtname01")
                .lastname("lastname01")
                .monthlyIncome(BigDecimal.valueOf(11000.0))
                .phone("5381234501")
                .birthday(new SimpleDateFormat("yyyy-MM-dd").parse("1998-11-01"))
                .guarantee(BigDecimal.valueOf(10000.0))
                .build();
        return customer4;
    }

    public static Customer getCustomer5() throws ParseException {
        Customer customer5 = Customer.builder()
                .id(2000L)
                .identityNo("10735264999")
                .firstname("firtname01")
                .lastname("lastname01")
                .monthlyIncome(BigDecimal.valueOf(5000.0))
                .phone("5381234501")
                .birthday(new SimpleDateFormat("yyyy-MM-dd").parse("1998-11-01"))
                .guarantee(BigDecimal.valueOf(10000.0))
                .build();
        return customer5;
    }

    public static CreditScore getCreditScoreByCustomer(Customer customer){
        return creditScoreEntityService.getCreditScoreByCustomer(customer);
    }
}
