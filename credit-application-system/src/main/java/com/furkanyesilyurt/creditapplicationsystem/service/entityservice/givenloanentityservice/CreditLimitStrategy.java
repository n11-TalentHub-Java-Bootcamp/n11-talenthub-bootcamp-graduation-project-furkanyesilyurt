package com.furkanyesilyurt.creditapplicationsystem.service.entityservice.givenloanentityservice;

import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.LoanResponseDto;

public interface CreditLimitStrategy {

    //condition1: credit score is below 500
    //condition2: credit score is between 500-1000 and monthly income is below 5000
    //condition3: credit score is between 500-1000 and monthly income is between 5000-10000
    //condition4: credit score is between 500-1000 and monthly income is above 10000
    //condition5: credit score is above 1000
    public LoanResponseDto getCreditLimit(Double guarantee, Double monthlyIncome);
}
