package com.furkanyesilyurt.creditapplicationsystem.controller;

import com.furkanyesilyurt.creditapplicationsystem.constant.ResponseStatus;
import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.LoanResponseDto;
import com.furkanyesilyurt.creditapplicationsystem.entity.Customer;
import com.furkanyesilyurt.creditapplicationsystem.provider.LoanDataProvider;
import com.furkanyesilyurt.creditapplicationsystem.repository.IGivenLoanDao;
import com.furkanyesilyurt.creditapplicationsystem.service.entityservice.CreditScoreEntityService;
import com.furkanyesilyurt.creditapplicationsystem.service.entityservice.CustomerEntityService;
import com.furkanyesilyurt.creditapplicationsystem.service.entityservice.givenloanentityservice.GivenLoanEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GivenLoanServiceTest {

    @InjectMocks
    private GivenLoanEntityService givenLoanEntityService;

    @Mock
    private IGivenLoanDao givenLoanDao;

    @Mock
    private CustomerEntityService customerEntityService;

    @InjectMocks
    private CreditScoreEntityService creditScoreEntityService;

    @Test
    void shouldCreditResponseRejected() throws ParseException {

        Customer customer = LoanDataProvider.getCustomer1();
        Long calculatedScore = creditScoreEntityService.calculateCreditScore(customer);

        LoanResponseDto loanResponseDto = givenLoanEntityService.getCreditLimit(calculatedScore,
                customer.getGuarantee().doubleValue(), customer.getMonthlyIncome().doubleValue());

        assertEquals(ResponseStatus.REJECTION, loanResponseDto.getResponseStatus());

    }

    //condition1: credit score is between 500-1000 and monthly income is below 5000
    //condition2: credit score is between 500-1000 and monthly income is between 5000-10000
    //condition3: credit score is between 500-1000 and monthly income is above 10000
    //condition4: credit score is above 1000
    @Test
    void shouldCreditResponseApprovalWithCondition1() throws ParseException {

        Customer customer = LoanDataProvider.getCustomer2();

        Long calculatedScore = creditScoreEntityService.calculateCreditScore(customer);
        LoanResponseDto loanResponseDto = givenLoanEntityService.getCreditLimit(calculatedScore,
                customer.getGuarantee().doubleValue(), customer.getMonthlyIncome().doubleValue());

        assertEquals(ResponseStatus.APPROVAL, loanResponseDto.getResponseStatus());
        assertEquals(11000.0, loanResponseDto.getCreditLimit().doubleValue());
    }

    @Test
    void shouldCreditResponseApprovalWithCondition2() throws ParseException {

        Customer customer = LoanDataProvider.getCustomer3();
        Long calculatedScore = creditScoreEntityService.calculateCreditScore(customer);

        LoanResponseDto loanResponseDto = givenLoanEntityService.getCreditLimit(calculatedScore,
                customer.getGuarantee().doubleValue(), customer.getMonthlyIncome().doubleValue());

        assertEquals(ResponseStatus.APPROVAL, loanResponseDto.getResponseStatus());
        assertEquals(22000.0, loanResponseDto.getCreditLimit().doubleValue());

    }

    @Test
    void shouldCreditResponseApprovalWithCondition3() throws ParseException {

        Customer customer = LoanDataProvider.getCustomer4();
        Long calculatedScore = creditScoreEntityService.calculateCreditScore(customer);

        LoanResponseDto loanResponseDto = givenLoanEntityService.getCreditLimit(calculatedScore,
                customer.getGuarantee().doubleValue(), customer.getMonthlyIncome().doubleValue());

        assertEquals(ResponseStatus.APPROVAL, loanResponseDto.getResponseStatus());
        assertEquals(24500.0, loanResponseDto.getCreditLimit().doubleValue());

    }

    @Test
    void shouldCreditResponseApprovalWithCondition4() throws ParseException {

        Customer customer = LoanDataProvider.getCustomer5();
        Long calculatedScore = creditScoreEntityService.calculateCreditScore(customer);

        LoanResponseDto loanResponseDto = givenLoanEntityService.getCreditLimit(calculatedScore,
                customer.getGuarantee().doubleValue(), customer.getMonthlyIncome().doubleValue());

        assertEquals(ResponseStatus.APPROVAL, loanResponseDto.getResponseStatus());
        assertEquals(25000.0, loanResponseDto.getCreditLimit().doubleValue());

    }


}