package com.furkanyesilyurt.creditapplicationsystem.provider;

import com.furkanyesilyurt.creditapplicationsystem.converter.ICustomerMapper;
import com.furkanyesilyurt.creditapplicationsystem.dto.customer.CustomerDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.customer.NewCustomerDto;
import com.furkanyesilyurt.creditapplicationsystem.entity.Customer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerDataProvider {

    public static List<Customer> getAllCustomerList() throws ParseException {

        List<Customer> customerList = new ArrayList<>();

        Customer customer1 = Customer.builder()
                .id(1001L)
                .identityNo("12345678901")
                .firstname("firtname01")
                .lastname("lastname01")
                .monthlyIncome(BigDecimal.valueOf(5000.0))
                .phone("5381234501")
                .birthday(new SimpleDateFormat("yyyy-MM-dd").parse("1998-11-01"))
                .guarantee(BigDecimal.valueOf(10000.0))
                .build();
        customerList.add(customer1);

        Customer customer2 = Customer.builder()
                .id(1002L)
                .identityNo("12345678902")
                .firstname("firtname02")
                .lastname("lastname02")
                .monthlyIncome(BigDecimal.valueOf(5500.0))
                .phone("5381234502")
                .birthday(new SimpleDateFormat("yyyy-MM-dd").parse("1998-11-02"))
                .guarantee(BigDecimal.valueOf(15000.0))
                .build();
        customerList.add(customer2);

        Customer customer3 = Customer.builder()
                .id(1003L)
                .identityNo("12345678903")
                .firstname("firtname03")
                .lastname("lastname03")
                .monthlyIncome(BigDecimal.valueOf(6000.0))
                .phone("5381234503")
                .birthday(new SimpleDateFormat("yyyy-MM-dd").parse("1998-11-03"))
                .guarantee(BigDecimal.valueOf(20000.0))
                .build();
        customerList.add(customer3);

        return customerList;
    }

    public static List<CustomerDto> convertCustomerListToCustomerDtoList(List<Customer> customers){
        List<CustomerDto> customerDtos = new ArrayList<>();
        for(Customer customer : customers){
            customerDtos.add(ICustomerMapper.INSTANCE.convertCustomerToCustomerDto(customer));
        }
        return customerDtos;
    }

    public static NewCustomerDto getNewCustomerDto() throws ParseException {
        NewCustomerDto newCustomerDto = NewCustomerDto.builder()
                .identityNo("12345678901")
                .firstname("firtname01")
                .lastname("lastname01")
                .monthlyIncome(BigDecimal.valueOf(5000.0))
                .phone("5381234501")
                .birthday(new SimpleDateFormat("yyyy-MM-dd").parse("1998-11-01"))
                .guarantee(BigDecimal.valueOf(10000.0))
                .build();

        return newCustomerDto;
    }

    public static Customer getCustomer() throws ParseException {
        Customer customer = Customer.builder()
                .id(1L)
                .identityNo("10735264930")
                .firstname("firtname01")
                .lastname("lastname01")
                .monthlyIncome(BigDecimal.valueOf(5000.0))
                .phone("5381234501")
                .birthday(new SimpleDateFormat("yyyy-MM-dd").parse("1998-11-01"))
                .guarantee(BigDecimal.valueOf(10000.0))
                .build();
        return customer;
    }

    public static CustomerDto convertCustomerToCustomerDto(Customer customer){
        return ICustomerMapper.INSTANCE.convertCustomerToCustomerDto(customer);
    }
}
