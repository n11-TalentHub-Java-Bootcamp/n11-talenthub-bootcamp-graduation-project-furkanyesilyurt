package com.furkanyesilyurt.creditapplicationsystem.dto.customer;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class CustomerDto {

    private Long id;
    private String identityNo;
    private String firstname;
    private String lastname;
    private BigDecimal monthlyIncome;
    private String phone;
    private Date birthday;
    private BigDecimal guarantee;

}
