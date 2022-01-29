package com.furkanyesilyurt.creditapplicationsystem.constant.creditlimitcalculationvariables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum IncomeBelow5000 {

    default_credit_limit(10000.0),
    guarantee_percent(0.1);

    private double value;

}
