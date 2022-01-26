package com.furkanyesilyurt.creditapplicationsystem.controller;

import com.furkanyesilyurt.creditapplicationsystem.dto.customer.CustomerDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.customer.NewCustomerDto;
import com.furkanyesilyurt.creditapplicationsystem.service.entityservice.CustomerEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Customer Controller", description = "Here we can list, save, delete and update customers.")
public class CustomerController {

    private final CustomerEntityService customerEntityService;

    @Operation(summary = "All customers are listed.")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        var customerDtos = customerEntityService.getAllCustomers();
        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

    @Operation(summary = "Save a customer")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody NewCustomerDto newCustomerDto){
        var customerDto = customerEntityService.saveCustomer(newCustomerDto);
        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a customer")
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<CustomerDto> deleteCustomer(
            @RequestParam @Parameter(name = "identityNo", description = "ID number of the customer to be deleted.") String identityNo){
        var customerDto = customerEntityService.deleteCustomer(identityNo);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @Operation(summary = "Update a customer")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<CustomerDto> updateCustomer(
            @RequestParam @Parameter(name = "identityNo", description = "ID number of the customer to be updated.") String identityNo,
            @RequestBody NewCustomerDto newCustomerDto){
        var customerDto = customerEntityService.updateCustomer(identityNo, newCustomerDto);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

}
