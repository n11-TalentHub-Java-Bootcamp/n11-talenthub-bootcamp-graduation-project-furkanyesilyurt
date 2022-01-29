package com.furkanyesilyurt.creditapplicationsystem.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Schema(name="NewCustomerDto", description = "the schema is where the new customer's information is written")
public class NewCustomerDto {

    private String identityNo;
    private String firstname;
    private String lastname;
    private BigDecimal monthlyIncome;
    private String phone;
//    private String email;
    private Date birthday;
    private BigDecimal guarantee;

}
