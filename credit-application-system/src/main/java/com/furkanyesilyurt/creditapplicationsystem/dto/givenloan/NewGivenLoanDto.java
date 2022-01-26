package com.furkanyesilyurt.creditapplicationsystem.dto.givenloan;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class NewGivenLoanDto {

    private Long customerId;
    private Long scoreId;
    private BigDecimal creditLimit;

}
