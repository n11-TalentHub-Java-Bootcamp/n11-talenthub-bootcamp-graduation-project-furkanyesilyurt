package com.furkanyesilyurt.creditapplicationsystem.dto.givencredit;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class NewGivenCreditDto {

    private Long customerId;
    private Long scoreId;
    private BigDecimal creditLimit;

}
