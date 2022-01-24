package com.furkanyesilyurt.creditapplicationsystem.service.entityservice;

import com.furkanyesilyurt.creditapplicationsystem.converter.IGivenCreditMapper;
import com.furkanyesilyurt.creditapplicationsystem.dto.givencredit.CreditResponseDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.givencredit.GivenCreditDto;
import com.furkanyesilyurt.creditapplicationsystem.dto.givencredit.NewGivenCreditDto;
import com.furkanyesilyurt.creditapplicationsystem.entity.CreditScore;
import com.furkanyesilyurt.creditapplicationsystem.entity.Customer;
import com.furkanyesilyurt.creditapplicationsystem.entity.GivenCredit;
import com.furkanyesilyurt.creditapplicationsystem.enums.Multipliers;
import com.furkanyesilyurt.creditapplicationsystem.enums.ResponseStatus;
import com.furkanyesilyurt.creditapplicationsystem.excepiton.customer.CustomerNotFoundException;
import com.furkanyesilyurt.creditapplicationsystem.excepiton.givencredit.GivenCreditNotFoundException;
import com.furkanyesilyurt.creditapplicationsystem.repository.IGivenCreditDao;
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
public class GivenCreditEntityService {

    private final IGivenCreditDao givenCreditDao;
    private final CustomerEntityService customerEntityService;
    private final CreditScoreEntityService creditScoreEntityService;

    public CreditResponseDto inquireCredit(String identityNo) {

        log.info("Request: {}", "Successful execution of request to application of loan.");

        Customer customer = customerEntityService.findCustomerByIdentityNo(identityNo);
        CreditScore creditScore = creditScoreEntityService.getCreditScoreByCustomer(customer);

        Double monthlyIncome = customer.getMonthlyIncome().doubleValue();
        Double guarantee = customer.getGuarantee().doubleValue();
        Long score = creditScore.getCreditScore();
        Double totalCreditLimit;

        if (score < 500) {
            log.warn("Customer's credit score is less than 500. Can't get a loan.");
            return new CreditResponseDto(ResponseStatus.REJECTION);

        } else if (score >= 500 && score < 1000) {

            if (monthlyIncome < 5000) {
                totalCreditLimit = calculateTotalCreditLimit(10000.0, guarantee, 0.1, monthlyIncome, score);
                saveGivenCredit(customer,creditScore, totalCreditLimit);
                sendSms();
                return new CreditResponseDto(ResponseStatus.APPROVAL, new BigDecimal(totalCreditLimit));

            } else if (monthlyIncome >= 5000 && monthlyIncome < 10000) {
                totalCreditLimit = calculateTotalCreditLimit(20000.0, guarantee, 0.2, monthlyIncome, score);
                saveGivenCredit(customer,creditScore, totalCreditLimit);
                sendSms();
                return new CreditResponseDto(ResponseStatus.APPROVAL, new BigDecimal(totalCreditLimit));

            } else {
                totalCreditLimit = calculateTotalCreditLimit(0.0, guarantee, 0.25, monthlyIncome, score);
                saveGivenCredit(customer,creditScore, totalCreditLimit);
                sendSms();
                return new CreditResponseDto(ResponseStatus.APPROVAL, new BigDecimal(totalCreditLimit));
            }

        } else {
            totalCreditLimit = calculateTotalCreditLimit(0.0, guarantee, 0.5, monthlyIncome, score);
            saveGivenCredit(customer,creditScore, totalCreditLimit);
            sendSms();
            return new CreditResponseDto(ResponseStatus.APPROVAL, new BigDecimal(totalCreditLimit));
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

    public void saveGivenCredit(Customer customer, CreditScore creditScore, Double creditLimit){
        NewGivenCreditDto newGivenCreditDto = new NewGivenCreditDto();

        newGivenCreditDto.setCustomerId(customer.getId());
        newGivenCreditDto.setScoreId(creditScore.getId());
        newGivenCreditDto.setCreditLimit(new BigDecimal(creditLimit));

        GivenCredit givenCredit = IGivenCreditMapper.INSTANCE.convertNewGivenCreditDtoToGivenCredit(newGivenCreditDto);

        log.info("Response: {}", "The credit of user ID " +customer.getIdentityNo() + " was recorded in the database.");

        givenCreditDao.save(givenCredit);
    }

    public void sendSms(){
        log.info("Informed by sms.");
    } //TODO: Send SMS method

    public List<GivenCreditDto> getApprovedCredit(String identityNo, String birthdays) throws ParseException {

        log.info("Request: {}", "Successful execution of request to find approved credit.");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse(birthdays);

        Boolean isExistsCustomer = customerEntityService.existsCustomerByIdentityNoAndBirthday(identityNo, birthday);
        if(!isExistsCustomer){
            log.error("Customer identityNo: " + identityNo +
                    " and customer birthday: " + birthday + "is not matching with each other.");
            throw new CustomerNotFoundException("Customer identityNo: " + identityNo +
                    " and customer birthday: " + birthday + "is not matching with each other.");
        }
        Customer customer = customerEntityService.findCustomerByIdentityNoAndBirthday(identityNo, birthday);

        Boolean isExistsGivenCredit = givenCreditDao.existsGivenCreditByCustomer(customer);
        if(!isExistsGivenCredit){
            log.error("The credit record of the user named " + customer.getFirstname() + " was not found.");
            throw new GivenCreditNotFoundException("The credit record of the user named " + customer.getFirstname() + " was not found.");
        }
        List<GivenCredit> givenCredits = givenCreditDao.findGivenCreditByCustomer(customer);

        List<GivenCreditDto> givenCreditDtos = new ArrayList<>();
        for(GivenCredit givenCredit : givenCredits){
            givenCreditDtos.add(IGivenCreditMapper.INSTANCE.convertGivenCreditToGivenCreditDto(givenCredit));
        }

        log.info("Response: {}", "Accepted credit of the customer whose birthday is " + birthday + " and ID number is " + identityNo);

        return givenCreditDtos;
    }

}
