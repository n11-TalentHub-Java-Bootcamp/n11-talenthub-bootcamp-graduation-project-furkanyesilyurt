package com.furkanyesilyurt.creditapplicationsystem.service.entityservice;

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
        Double totalCreditLimit;

        if (score < 500) {
            log.warn("Customer's credit score is less than 500. Can't get a loan.");
            return new LoanResponseDto(ResponseStatus.REJECTION);

        } else if (score >= 500 && score < 1000) {

            if (monthlyIncome < 5000) {
                totalCreditLimit = calculateTotalCreditLimit(10000.0, guarantee, 0.1, monthlyIncome, score);
                saveGivenLoan(customer,creditScore, totalCreditLimit);
                sendSms();
                return new LoanResponseDto(ResponseStatus.APPROVAL, new BigDecimal(totalCreditLimit));

            } else if (monthlyIncome >= 5000 && monthlyIncome < 10000) {
                totalCreditLimit = calculateTotalCreditLimit(20000.0, guarantee, 0.2, monthlyIncome, score);
                saveGivenLoan(customer,creditScore, totalCreditLimit);
                sendSms();
                return new LoanResponseDto(ResponseStatus.APPROVAL, new BigDecimal(totalCreditLimit));

            } else {
                totalCreditLimit = calculateTotalCreditLimit(0.0, guarantee, 0.25, monthlyIncome, score);
                saveGivenLoan(customer,creditScore, totalCreditLimit);
                sendSms();
                return new LoanResponseDto(ResponseStatus.APPROVAL, new BigDecimal(totalCreditLimit));
            }

        } else {
            totalCreditLimit = calculateTotalCreditLimit(0.0, guarantee, 0.5, monthlyIncome, score);
            saveGivenLoan(customer,creditScore, totalCreditLimit);
            sendSms();
            return new LoanResponseDto(ResponseStatus.APPROVAL, new BigDecimal(totalCreditLimit));
        }

    }

    public Double calculateTotalCreditLimit(Double creditLimit, Double guarantee, Double guaranteePercent, Double monthlyIncome, Long score) {
        Double totalLimit;

        if (score >= 1000) {
            totalLimit = monthlyIncome * Multipliers.CREDIT_LIMIT_MULTIPLIER.getMultiplier();

        } else {

            if (monthlyIncome >= 10000) {
                totalLimit = monthlyIncome * Multipliers.CREDIT_LIMIT_MULTIPLIER.getMultiplier() / 2;
            } else {
                totalLimit = creditLimit;
            }

        }

        if (guarantee != null) {
            totalLimit += guarantee * guaranteePercent;
        }

        return totalLimit;
    }

    public void saveGivenLoan(Customer customer, CreditScore creditScore, Double creditLimit){
        NewGivenLoanDto newGivenLoanDto = new NewGivenLoanDto();

        newGivenLoanDto.setCustomerId(customer.getId());
        newGivenLoanDto.setScoreId(creditScore.getId());
        newGivenLoanDto.setCreditLimit(new BigDecimal(creditLimit));

        GivenLoan givenLoan = IGivenLoanMapper.INSTANCE.convertNewGivenLoanDtoToGivenLoan(newGivenLoanDto);

        log.info("Response: {}", "The loan of user ID " +customer.getIdentityNo() + " was recorded in the database.");

        givenLoanDao.save(givenLoan);
    }

    public void sendSms(){
        log.info("Informed by sms.");
    } //TODO: Send SMS method

    public List<GivenLoanDto> getApprovedLoan(String identityNo, String birthdays) throws ParseException {

        log.info("Request: {}", "Successful execution of request to find approved loan.");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse(birthdays);

        Boolean isExistsCustomer = customerEntityService.existsCustomerByIdentityNoAndBirthday(identityNo, birthday);
        if(!isExistsCustomer){
            log.error("Customer identityNo: " + identityNo +
                    " and customer birthday: " + birthday + " is not matching with each other.");
            throw new CustomerNotFoundException("Customer identityNo: " + identityNo +
                    " and customer birthday: " + birthday + " is not matching with each other.");
        }
        Customer customer = customerEntityService.findCustomerByIdentityNoAndBirthday(identityNo, birthday);

        Boolean isExistsGivenLoan = givenLoanDao.existsGivenLoanByCustomer(customer);
        if(!isExistsGivenLoan){
            log.error("The credit record of the user named " + customer.getFirstname() + " was not found.");
            throw new GivenCreditNotFoundException("The credit record of the user named " + customer.getFirstname() + " was not found.");
        }
        List<GivenLoan> givenLoans = givenLoanDao.findGivenLoanByCustomer(customer);

        List<GivenLoanDto> givenLoanDtos = new ArrayList<>();
        for(GivenLoan givenLoan : givenLoans){
            givenLoanDtos.add(IGivenLoanMapper.INSTANCE.convertGivenLoanToGivenLoanDto(givenLoan));
        }

        log.info("Response: {}", "Accepted loan of the customer whose birthday is " + birthday + " and ID number is " + identityNo);

        return givenLoanDtos;
    }

}
