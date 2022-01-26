package com.furkanyesilyurt.creditapplicationsystem.converter;

import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.GivenLoanDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.NewGivenLoanDto;
import com.furkanyesilyurt.creditapplicationsystem.entity.GivenLoan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IGivenLoanMapper {

    IGivenLoanMapper INSTANCE = Mappers.getMapper(IGivenLoanMapper.class);

    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "scoreId", target = "creditScore.id")
    GivenLoan convertNewGivenLoanDtoToGivenLoan(NewGivenLoanDto newGivenLoanDto);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "scoreId", source = "creditScore.id")
    GivenLoanDto convertGivenLoanToGivenLoanDto(GivenLoan givenLoan);

}
