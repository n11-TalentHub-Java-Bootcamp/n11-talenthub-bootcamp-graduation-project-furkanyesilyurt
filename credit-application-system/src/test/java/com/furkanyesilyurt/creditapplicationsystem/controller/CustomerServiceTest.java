package com.furkanyesilyurt.creditapplicationsystem.controller;

import com.furkanyesilyurt.creditapplicationsystem.dto.customer.CustomerDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.customer.NewCustomerDto;
import com.furkanyesilyurt.creditapplicationsystem.entity.Customer;
import com.furkanyesilyurt.creditapplicationsystem.excepiton.customer.CustomerNotFoundException;
import com.furkanyesilyurt.creditapplicationsystem.provider.CustomerDataProvider;
import com.furkanyesilyurt.creditapplicationsystem.repository.ICustomerDao;
import com.furkanyesilyurt.creditapplicationsystem.service.entityservice.CreditScoreEntityService;
import com.furkanyesilyurt.creditapplicationsystem.service.entityservice.CustomerEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerEntityService customerEntityService;

    @Mock
    private ICustomerDao customerDao;

    @Mock
    private CreditScoreEntityService creditScoreEntityService;

    @Test
    void shouldfindAllExist() throws ParseException {

        List<Customer> customerList = CustomerDataProvider.getAllCustomerList();
        List<CustomerDto> customerDtos = CustomerDataProvider.convertCustomerListToCustomerDtoList(customerList);

        when(customerDao.findAll()).thenReturn(customerList);

        List<CustomerDto> respCustomerDtos = customerEntityService.getAllCustomers();

        assertArrayEquals(customerDtos.toArray(), respCustomerDtos.toArray());
    }

    @Test
    void shouldfindAllDoesNotExist(){
        List<Customer> arrayList = new ArrayList<>();
        List<CustomerDto> customerDtoList = CustomerDataProvider.convertCustomerListToCustomerDtoList(arrayList);

        when(customerDao.findAll()).thenReturn(arrayList);

        Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
            List<CustomerDto> responseCustomerDtoList = customerEntityService.getAllCustomers();
        });

        String expectedMessage = "There are no customers to list.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void saveCustomer() throws ParseException {
        NewCustomerDto newCustomerDto = CustomerDataProvider.getNewCustomerDto();
        Customer customer = CustomerDataProvider.getCustomer();
        CustomerDto customerDtoSaved = CustomerDataProvider.convertCustomerToCustomerDto(customer);

        when(customerDao.save(ArgumentMatchers.any(Customer.class))).thenReturn(customer);

        CustomerDto resp = customerEntityService.saveCustomer(newCustomerDto);

        assertEquals(customerDtoSaved, resp);
        assertEquals(1L, resp.getId());
    }


}