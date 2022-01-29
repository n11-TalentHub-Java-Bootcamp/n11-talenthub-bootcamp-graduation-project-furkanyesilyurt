package com.furkanyesilyurt.creditapplicationsystem.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Schema(name="CustomerDto", description = "the scheme in which customers are listed")
public class CustomerDto {

    private Long id;
    private String identityNo;
    private String firstname;
    private String lastname;
    private BigDecimal monthlyIncome;
    private String phone;
//    private String email;
    private Date birthday;
    private BigDecimal guarantee;

}
