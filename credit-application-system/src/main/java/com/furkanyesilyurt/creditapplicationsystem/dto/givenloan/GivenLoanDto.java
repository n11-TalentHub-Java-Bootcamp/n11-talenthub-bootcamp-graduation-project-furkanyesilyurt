package com.furkanyesilyurt.creditapplicationsystem.dto.givenloan;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(name="GivenLoanDto", description = "The scheme by which the approved loan is viewed")
public class GivenLoanDto {

    private Long id;
    private Long customerId;
    private Long scoreId;
    private BigDecimal creditLimit;

}
