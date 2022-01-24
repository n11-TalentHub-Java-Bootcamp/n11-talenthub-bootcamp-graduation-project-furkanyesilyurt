package com.furkanyesilyurt.creditapplicationsystem.controller;

import com.furkanyesilyurt.creditapplicationsystem.dto.customer.CustomerDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.customer.NewCustomerDto;
import com.furkanyesilyurt.creditapplicationsystem.service.entityservice.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {

    private final CustomerEntityService customerEntityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        var customerDtos = customerEntityService.getAllCustomers();
        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody NewCustomerDto newCustomerDto){
        var customerDto = customerEntityService.saveCustomer(newCustomerDto);
        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<CustomerDto> deleteCustomer(@RequestParam String identityNo){
        var customerDto = customerEntityService.deleteCustomer(identityNo);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<CustomerDto> updateCustomer(@RequestParam String identityNo, @RequestBody NewCustomerDto newCustomerDto){
        var customerDto = customerEntityService.updateCustomer(identityNo, newCustomerDto);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

}
