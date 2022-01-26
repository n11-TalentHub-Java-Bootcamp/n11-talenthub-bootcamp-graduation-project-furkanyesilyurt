package com.furkanyesilyurt.creditapplicationsystem.service.entityservice;

import com.furkanyesilyurt.creditapplicationsystem.excepiton.customer.IdentityNoIsTooShortException;
import com.furkanyesilyurt.creditapplicationsystem.repository.ICreditScoreDao;
import com.furkanyesilyurt.creditapplicationsystem.entity.CreditScore;
import com.furkanyesilyurt.creditapplicationsystem.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditScoreEntityService {

    private final ICreditScoreDao creditScoreDao;

    public Long calculateCreditScore(Customer customer){
        //12345651474 -> 74 * 10 + 100 = 840
        if(customer.getIdentityNo().length() < 3){
            throw new IdentityNoIsTooShortException("The identity no is too short. Recommended length is 11 characters.");
        }
        var beginIndex = customer.getIdentityNo().length() - 2;
        var endIndex = customer.getIdentityNo().length();
        Long calculatedScore = Long.parseLong(customer.getIdentityNo().substring(beginIndex,endIndex)) * 10 + 100;
        return calculatedScore;
    }

    public void saveCreditScore(Long score, Customer customer){
        CreditScore creditScore = new CreditScore();
        creditScore.setCreditScore(score);
        creditScore.setCustomer(customer);
        creditScoreDao.save(creditScore);
        log.info("Credit score " + creditScore.getCreditScore() + " was successfully saved.");
    }


    public void updateCreditScore(CreditScore creditScore){
        creditScoreDao.save(creditScore);
        log.info("Credit score " + creditScore.getCreditScore() + " was successfully saved.");
    }

    public void deleteByCustomer(Customer customer){
        creditScoreDao.deleteByCustomer(customer);
    }

    public CreditScore getCreditScoreByCustomer(Customer customer){
        return creditScoreDao.findCreditScoreByCustomer(customer);
    }

}
