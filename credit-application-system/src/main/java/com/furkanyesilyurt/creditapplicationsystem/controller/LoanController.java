package com.furkanyesilyurt.creditapplicationsystem.controller;

import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.GivenLoanDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.LoanResponseDto;
import com.furkanyesilyurt.creditapplicationsystem.service.entityservice.GivenLoanEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Loan Controller", description = "Here we can apply for a loan and inquire about the loan.")
public class LoanController {

    private final GivenLoanEntityService givenLoanEntityService;

    @Operation(summary = "Apply for a loan with the customer's identification number.")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<LoanResponseDto> inquireLoan(
            @RequestParam @Parameter(name = "identityNo", description = "The identification number of the relevant customer.") String identityNo){
        var responseDto = givenLoanEntityService.inquireCredit(identityNo);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "See approved loan application the customer's identification number.")
    @RequestMapping(value = "/approved-credit", method = RequestMethod.GET)
    public ResponseEntity<List<GivenLoanDto>> getApprovedLoan(
            @RequestParam @Parameter(name = "identityNo", description = "The identification number of the relevant customer.") String identityNo,
            @RequestParam @Parameter(name = "birthday", description = "The birthday date of the relevant customer. Format yyyy-MM-dd.") String birthday)
            throws ParseException {
        var responseDto = givenLoanEntityService.getApprovedLoan(identityNo, birthday);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
