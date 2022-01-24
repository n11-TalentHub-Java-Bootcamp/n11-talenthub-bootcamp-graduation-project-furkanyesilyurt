package com.furkanyesilyurt.creditapplicationsystem.converter;

import com.furkanyesilyurt.creditapplicationsystem.dto.givencredit.GivenCreditDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.givencredit.NewGivenCreditDto;
import com.furkanyesilyurt.creditapplicationsystem.entity.GivenCredit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IGivenCreditMapper {

    IGivenCreditMapper INSTANCE = Mappers.getMapper(IGivenCreditMapper.class);

    @Mapping(source = "newGivenCreditDto.customerId", target = "customer.id")
    @Mapping(source = "newGivenCreditDto.scoreId", target = "creditScore.id")
    GivenCredit convertNewGivenCreditDtoToGivenCredit(NewGivenCreditDto newGivenCreditDto);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "scoreId", source = "creditScore.id")
    GivenCreditDto convertGivenCreditToGivenCreditDto(GivenCredit givenCredit);

}
