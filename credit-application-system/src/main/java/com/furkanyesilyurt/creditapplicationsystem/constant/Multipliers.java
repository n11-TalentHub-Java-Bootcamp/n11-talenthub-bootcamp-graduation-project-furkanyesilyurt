package com.furkanyesilyurt.creditapplicationsystem.constant;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Multipliers {

    //default credit limit multiplier is 4
    CREDIT_LIMIT_MULTIPLIER(4.0);

    private double multiplier;

}
