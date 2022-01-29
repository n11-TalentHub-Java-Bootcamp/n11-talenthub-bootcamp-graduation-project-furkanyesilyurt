package com.furkanyesilyurt.creditapplicationsystem.constant.creditlimitcalculationvariables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum IncomeAbove10000 {

    guarantee_percent(0.25);

    private double value;

}