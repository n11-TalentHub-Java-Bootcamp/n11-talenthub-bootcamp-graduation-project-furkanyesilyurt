package com.furkanyesilyurt.creditapplicationsystem.service.entityservice.givenloanentityservice;

import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.LoanResponseDto;

public class Context {

    private CreditLimitStrategy creditLimitStrategy;

    public void setCreditLimitStrategy(CreditLimitStrategy creditLimitStrategy) {
        this.creditLimitStrategy = creditLimitStrategy;
    }

    public LoanResponseDto execute(Double guarantee, Double monthlyIncome){
        return creditLimitStrategy.getCreditLimit(guarantee, monthlyIncome);
    }

}
