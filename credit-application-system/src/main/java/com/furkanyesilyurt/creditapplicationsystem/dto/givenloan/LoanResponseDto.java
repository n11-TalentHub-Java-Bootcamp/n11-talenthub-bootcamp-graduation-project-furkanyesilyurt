package com.furkanyesilyurt.creditapplicationsystem.dto.givenloan;

import com.furkanyesilyurt.creditapplicationsystem.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class LoanResponseDto {

    private ResponseStatus responseStatus;
    private BigDecimal creditLimit;

    public LoanResponseDto(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

}
