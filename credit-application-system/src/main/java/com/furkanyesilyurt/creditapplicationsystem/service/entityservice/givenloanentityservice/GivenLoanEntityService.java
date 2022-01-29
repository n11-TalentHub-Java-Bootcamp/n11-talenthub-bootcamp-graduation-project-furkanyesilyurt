package com.furkanyesilyurt.creditapplicationsystem.service.entityservice.givenloanentityservice;

import com.furkanyesilyurt.creditapplicationsystem.converter.IGivenLoanMapper;
import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.GivenLoanDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.LoanResponseDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.givenloan.NewGivenLoanDto;
import com.furkanyesilyurt.creditapplicationsystem.entity.CreditScore;
import com.furkanyesilyurt.creditapplicationsystem.entity.Customer;
import com.furkanyesilyurt.creditapplicationsystem.entity.GivenLoan;
import com.furkanyesilyurt.creditapplicationsystem.constant.Multipliers;
import com.furkanyesilyurt.creditapplicationsystem.constant.ResponseStatus;
import com.furkanyesilyurt.creditapplicationsystem.excepiton.customer.CustomerNotFoundException;
import com.furkanyesilyurt.creditapplicationsystem.excepiton.givenloan.GivenCreditNotFoundException;
import com.furkanyesilyurt.creditapplicationsystem.repository.IGivenLoanDao;
import com.furkanyesilyurt.creditapplicationsystem.service.entityservice.CreditScoreEntityService;
import com.furkanyesilyurt.creditapplicationsystem.service.entityservice.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GivenLoanEntityService {

    private final IGivenLoanDao givenLoanDao;
    private final CustomerEntityService customerEntityService;
    private final CreditScoreEntityService creditScoreEntityService;

    public LoanResponseDto inquireCredit(String identityNo) {

        log.info("Request: {}", "Successful execution of request to application of loan.");

        Customer customer = customerEntityService.findCustomerByIdentityNo(identityNo);
        CreditScore creditScore = creditScoreEntityService.getCreditScoreByCustomer(customer);

        Double monthlyIncome = customer.getMonthlyIncome().doubleValue();
        Double guarantee = customer.getGuarantee().doubleValue();
        Long score = creditScore.getCreditScore();

        LoanResponseDto responseDto = getCreditLimit(score, guarantee, monthlyIncome);
        if (responseDto.getResponseStatus() != ResponseStatus.REJECTION) {
            saveGivenLoan(customer, creditScore, responseDto.getCreditLimit().doubleValue());
            sendSms();
        }

        return responseDto;
    }

    public LoanResponseDto getCreditLimit(Long score, Double guarantee, Double monthlyIncome) {
        Context context = new Context();

        if (score < 500) {
            context.setCreditLimitStrategy(new CalculateCreditLimitScoreBelow500());

        } else if (score >= 500 && score < 1000) {

            if (monthlyIncome < 5000) {
                context.setCreditLimitStrategy(new CalculateCreditLimitIncomeBelow5000());

            } else if (monthlyIncome >= 5000 && monthlyIncome < 10000) {
                context.setCreditLimitStrategy(new CalculateCreditLimitIncomeBelow10000());

            } else {
                context.setCreditLimitStrategy(new CalculateCreditLimitIncomeAbove10000());
            }

        } else {
            context.setCreditLimitStrategy(new CalculateCreditLimitScoreAbove1000());
        }

        return context.execute(guarantee, monthlyIncome);
    }

    public void saveGivenLoan(Customer customer, CreditScore creditScore, Double creditLimit) {
        NewGivenLoanDto newGivenLoanDto = new NewGivenLoanDto();

        newGivenLoanDto.setCustomerId(customer.getId());
        newGivenLoanDto.setScoreId(creditScore.getId());
        newGivenLoanDto.setCreditLimit(new BigDecimal(creditLimit));

        GivenLoan givenLoan = IGivenLoanMapper.INSTANCE.convertNewGivenLoanDtoToGivenLoan(newGivenLoanDto);

        log.info("Response: {}", "The loan of user ID " + customer.getIdentityNo() + " was recorded in the database.");

        givenLoanDao.save(givenLoan);
    }

    public void sendSms() {
        log.info("Informed by sms.");
    }

    public List<GivenLoanDto> getApprovedLoan(String identityNo, String birthdays) throws ParseException {

        log.info("Request: {}", "Successful execution of request to find approved loan.");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse(birthdays);

        Boolean isExistsCustomer = customerEntityService.existsCustomerByIdentityNoAndBirthday(identityNo, birthday);
        if (!isExistsCustomer) {
            log.error("Customer identityNo: " + identityNo +
                    " and customer birthday: " + birthday + " is not matching with each other.");
            throw new CustomerNotFoundException("Customer identityNo: " + identityNo +
                    " and customer birthday: " + birthday + " is not matching with each other.");
        }
        Customer customer = customerEntityService.findCustomerByIdentityNoAndBirthday(identityNo, birthday);

        Boolean isExistsGivenLoan = givenLoanDao.existsGivenLoanByCustomer(customer);
        if (!isExistsGivenLoan) {
            log.error("The credit record of the user named " + customer.getFirstname() + " was not found.");
            throw new GivenCreditNotFoundException("The credit record of the user named " + customer.getFirstname() + " was not found.");
        }
        List<GivenLoan> givenLoans = givenLoanDao.findGivenLoanByCustomer(customer);

        List<GivenLoanDto> givenLoanDtos = new ArrayList<>();
        for (GivenLoan givenLoan : givenLoans) {
            givenLoanDtos.add(IGivenLoanMapper.INSTANCE.convertGivenLoanToGivenLoanDto(givenLoan));
        }

        log.info("Response: {}", "Accepted loan of the customer whose birthday is " + birthday + " and ID number is " + identityNo);

        return givenLoanDtos;
    }

}
