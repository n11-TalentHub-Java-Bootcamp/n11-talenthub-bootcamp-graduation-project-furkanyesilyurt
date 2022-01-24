package com.furkanyesilyurt.creditapplicationsystem.dto.creditscore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditScoreDto {

    private Long id;
    private Long customerId;
    private Long creditScore;

}
