package com.furkanyesilyurt.creditapplicationsystem.dto.givenloan;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GivenLoanDto {

    private Long id;
    private Long customerId;
    private Long scoreId;
    private BigDecimal creditLimit;

}
