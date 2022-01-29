package com.furkanyesilyurt.creditapplicationsystem.constant.creditlimitcalculationvariables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum IncomeBelow10000 {

    default_credit_limit(20000.0),
    guarantee_percent(0.2);

    private double value;

}
