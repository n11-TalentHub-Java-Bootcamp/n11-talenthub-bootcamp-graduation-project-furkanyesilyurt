package com.furkanyesilyurt.creditapplicationsystem.dto.givencredit;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GivenCreditDto {

    private Long id;
    private Long customerId;
    private Long scoreId;
    private BigDecimal creditLimit;

}
