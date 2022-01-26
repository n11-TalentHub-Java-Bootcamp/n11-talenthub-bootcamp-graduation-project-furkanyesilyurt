package com.furkanyesilyurt.creditapplicationsystem.controller;

import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.GivenLoanDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.LoanResponseDto;
import com.furkanyesilyurt.creditapplicationsystem.service.entityservice.GivenLoanEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class CreditController {

    private final GivenLoanEntityService givenLoanEntityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<LoanResponseDto> inquireCredit(@RequestParam String identityNo){
        var responseDto = givenLoanEntityService.inquireCredit(identityNo);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/approved-credit", method = RequestMethod.GET)
    public ResponseEntity<List<GivenLoanDto>> getApprovedCredit(@RequestParam String identityNo, @RequestParam String birthday) throws ParseException {
        var responseDto = givenLoanEntityService.getApprovedLoan(identityNo, birthday);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
