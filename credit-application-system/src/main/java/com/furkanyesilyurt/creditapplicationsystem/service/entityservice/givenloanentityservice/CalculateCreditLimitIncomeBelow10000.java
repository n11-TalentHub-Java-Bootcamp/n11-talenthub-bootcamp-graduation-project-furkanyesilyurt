package com.furkanyesilyurt.creditapplicationsystem.service.entityservice.givenloanentityservice;

import com.furkanyesilyurt.creditapplicationsystem.constant.ResponseStatus;
import com.furkanyesilyurt.creditapplicationsystem.constant.creditlimitcalculationvariables.IncomeBelow10000;
import com.furkanyesilyurt.creditapplicationsystem.constant.creditlimitcalculationvariables.IncomeBelow5000;
import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.LoanResponseDto;

import java.math.BigDecimal;

public class CalculateCreditLimitIncomeBelow10000 implements CreditLimitStrategy{
    @Override
    public LoanResponseDto getCreditLimit(Double guarantee, Double monthlyIncome) {
        Double totalLimit;
        totalLimit = IncomeBelow10000.default_credit_limit.getValue();

        if (guarantee != null) {
            totalLimit += guarantee * IncomeBelow10000.guarantee_percent.getValue();
        }

        return new LoanResponseDto(ResponseStatus.APPROVAL, new BigDecimal(totalLimit));
    }
}
