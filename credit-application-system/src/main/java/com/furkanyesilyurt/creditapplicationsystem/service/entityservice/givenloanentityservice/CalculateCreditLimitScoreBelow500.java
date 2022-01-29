package com.furkanyesilyurt.creditapplicationsystem.service.entityservice.givenloanentityservice;

import com.furkanyesilyurt.creditapplicationsystem.constant.ResponseStatus;
import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.LoanResponseDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculateCreditLimitScoreBelow500 implements CreditLimitStrategy{

    @Override
    public LoanResponseDto getCreditLimit(Double guarantee, Double monthlyIncome) {
        log.warn("Customer's credit score is less than 500. Can't get a loan.");
        return new LoanResponseDto(ResponseStatus.REJECTION);
    }
}
