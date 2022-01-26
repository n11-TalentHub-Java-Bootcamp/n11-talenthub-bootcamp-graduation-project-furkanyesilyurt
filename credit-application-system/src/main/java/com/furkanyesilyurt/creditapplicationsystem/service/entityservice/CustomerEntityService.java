package com.furkanyesilyurt.creditapplicationsystem.service.entityservice;

import com.furkanyesilyurt.creditapplicationsystem.excepiton.customer.IdentityNoIsTooShortException;
import com.furkanyesilyurt.creditapplicationsystem.repository.ICustomerDao;
import com.furkanyesilyurt.creditapplicationsystem.dto.customer.CustomerDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.customer.NewCustomerDto;
import com.furkanyesilyurt.creditapplicationsystem.entity.CreditScore;
import com.furkanyesilyurt.creditapplicationsystem.entity.Customer;
import com.furkanyesilyurt.creditapplicationsystem.excepiton.customer.CustomerNotFoundException;
import com.furkanyesilyurt.creditapplicationsystem.converter.ICustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerEntityService {

    private final ICustomerDao customerDao;
    private final CreditScoreEntityService creditScoreEntityService;

    public List<CustomerDto> getAllCustomers(){

        log.info("Request: {}", "Successful execution of request to get all customers.");

        List<Customer> customers = customerDao.findAll();
        if(customers.isEmpty()){
            log.error("There are no customers to list.");
            throw new CustomerNotFoundException("There are no customers to list.");
        }
        List<CustomerDto> customerDtos = new ArrayList<>();
        for(Customer customer : customers){
            customerDtos.add(ICustomerMapper.INSTANCE.convertCustomerToCustomerDto(customer));
        }

        log.info("Response: {}", "All customers have been brought.");

        return customerDtos;
    }

    public CustomerDto saveCustomer(NewCustomerDto newCustomerDto){

        log.info("Request: {}", "Successful execution of request to save a customer.");

        Customer customer = ICustomerMapper.INSTANCE.convertNewCustomerDtoToCustomer(newCustomerDto);
        customer = customerDao.save(customer);
        Long score = creditScoreEntityService.calculateCreditScore(customer);
        creditScoreEntityService.saveCreditScore(score, customer);

        log.info("Response: {}", "Customer with ID " + customer.getIdentityNo() + " is successfully registered.");

        return ICustomerMapper.INSTANCE.convertCustomerToCustomerDto(customer);
    }

    public CustomerDto deleteCustomer(String identityNo){

        log.info("Request: {}", "Successful execution of request to delete a customer.");

        Boolean isExists = customerDao.existsByIdentityNo(identityNo);
        if(!isExists){
            log.error("Customer with identity number " + identityNo + " could not be found.");
            throw new CustomerNotFoundException("Customer with identity number " + identityNo + " could not be found.");
        }

        var customer = customerDao.findCustomerByIdentityNo(identityNo);
        creditScoreEntityService.deleteByCustomer(customer);
        customerDao.deleteByIdentityNo(identityNo);

        log.info("Response: {}", "Customer with ID " + customer.getIdentityNo() + " is successfully deleted.");

        return ICustomerMapper.INSTANCE.convertCustomerToCustomerDto(customer);
    }

    public CustomerDto updateCustomer(String identityNo, NewCustomerDto newCustomerDto){

        log.info("Request: {}", "Successful execution of request to update a customer.");

        Boolean isExists = customerDao.existsByIdentityNo(identityNo);
        if(!isExists){
            log.error("Customer with identity number " + identityNo + " could not be found.");
            throw new CustomerNotFoundException("Customer with identity number " + identityNo + " could not be found.");
        }
        var customer = customerDao.findCustomerByIdentityNo(identityNo);

        customer.setIdentityNo(newCustomerDto.getIdentityNo());
        customer.setFirstname(newCustomerDto.getFirstname());
        customer.setLastname(newCustomerDto.getLastname());
        customer.setMonthlyIncome(newCustomerDto.getMonthlyIncome());
        customer.setPhone(newCustomerDto.getPhone());
        customer.setBirthday(newCustomerDto.getBirthday());
        customer.setGuarantee(newCustomerDto.getGuarantee());
        customer = customerDao.save(customer);

        var creditScore = creditScoreEntityService.getCreditScoreByCustomer(customer);
        Long score = creditScoreEntityService.calculateCreditScore(customer);
        creditScore.setCreditScore(score);
        creditScoreEntityService.updateCreditScore(creditScore);

        log.info("Response: {}", "Customer with ID " + customer.getIdentityNo() + " is successfully updated.");

        return ICustomerMapper.INSTANCE.convertCustomerToCustomerDto(customer);
    }

    public Customer findCustomerByIdentityNo(String identityNo){
        return customerDao.findCustomerByIdentityNo(identityNo);
    }

    public Boolean existsCustomerByIdentityNoAndBirthday(String identityNo, Date birthday){
        return customerDao.existsCustomerByIdentityNoAndBirthday(identityNo, birthday);
    }

    public Customer findCustomerByIdentityNoAndBirthday(String identityNo, Date birthday){
        return customerDao.findCustomerByIdentityNoAndBirthday(identityNo, birthday);
    }

}
