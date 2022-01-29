package com.furkanyesilyurt.creditapplicationsystem.service.entityservice.givenloanentityservice;

import com.furkanyesilyurt.creditapplicationsystem.constant.Multipliers;
import com.furkanyesilyurt.creditapplicationsystem.constant.ResponseStatus;
import com.furkanyesilyurt.creditapplicationsystem.constant.creditlimitcalculationvariables.IncomeAbove10000;
import com.furkanyesilyurt.creditapplicationsystem.constant.creditlimitcalculationvariables.IncomeBelow10000;
import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.LoanResponseDto;

import java.math.BigDecimal;

public class CalculateCreditLimitIncomeAbove10000 implements CreditLimitStrategy{
    @Override
    public LoanResponseDto getCreditLimit(Double guarantee, Double monthlyIncome) {
        Double totalLimit;
        totalLimit = monthlyIncome * Multipliers.CREDIT_LIMIT_MULTIPLIER.getMultiplier() / 2;

        if (guarantee != null) {
            totalLimit += guarantee * IncomeAbove10000.guarantee_percent.getValue();
        }

        return new LoanResponseDto(ResponseStatus.APPROVAL, new BigDecimal(totalLimit));
    }
}
