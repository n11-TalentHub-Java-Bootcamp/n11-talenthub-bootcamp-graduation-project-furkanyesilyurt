package com.furkanyesilyurt.creditapplicationsystem.controller;

import com.furkanyesilyurt.creditapplicationsystem.dto.givencredit.CreditResponseDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.givencredit.GivenCreditDto;
import com.furkanyesilyurt.creditapplicationsystem.service.entityservice.GivenCreditEntityService;
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
@RequestMapping("/api/v1/credits")
@RequiredArgsConstructor
public class CreditController {

    private final GivenCreditEntityService givenCreditEntityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<CreditResponseDto> inquireCredit(@RequestParam String identityNo){
        var responseDto = givenCreditEntityService.inquireCredit(identityNo);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/approved-credit", method = RequestMethod.GET)
    public ResponseEntity<List<GivenCreditDto>> getApprovedCredit(@RequestParam String identityNo, @RequestParam String birthday) throws ParseException {
        var responseDto = givenCreditEntityService.getApprovedCredit(identityNo, birthday);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
