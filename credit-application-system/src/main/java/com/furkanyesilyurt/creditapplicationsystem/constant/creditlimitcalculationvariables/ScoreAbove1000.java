package com.furkanyesilyurt.creditapplicationsystem.constant.creditlimitcalculationvariables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ScoreAbove1000 {

    guarantee_percent(0.5);

    private double value;
}
