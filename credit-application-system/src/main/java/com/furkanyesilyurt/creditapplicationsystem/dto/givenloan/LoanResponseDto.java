package com.furkanyesilyurt.creditapplicationsystem.dto.givenloan;

import com.furkanyesilyurt.creditapplicationsystem.constant.ResponseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@Schema(name="LoanResponseDto", description = "the response to the loan application")
public class LoanResponseDto {

    private ResponseStatus responseStatus;
    private BigDecimal creditLimit;

    public LoanResponseDto(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

}
