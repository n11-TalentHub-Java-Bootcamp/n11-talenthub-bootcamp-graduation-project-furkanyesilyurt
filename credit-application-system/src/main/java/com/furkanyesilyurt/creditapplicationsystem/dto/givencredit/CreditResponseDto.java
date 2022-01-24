package com.furkanyesilyurt.creditapplicationsystem.dto.givencredit;

import com.furkanyesilyurt.creditapplicationsystem.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class CreditResponseDto {

    private ResponseStatus responseStatus;
    private BigDecimal creditLimit;

    public CreditResponseDto(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

}
