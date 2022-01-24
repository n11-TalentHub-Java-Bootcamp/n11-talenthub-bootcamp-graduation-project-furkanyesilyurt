package com.furkanyesilyurt.creditapplicationsystem.service.entityservice;

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

    public void saveCreditScore(CreditScore creditScore){
        log.info("Credit score " + creditScore.getCreditScore() + " was successfully saved.");
        creditScoreDao.save(creditScore);
    }

    public void deleteByCustomer(Customer customer){
        creditScoreDao.deleteByCustomer(customer);
    }

    public CreditScore getCreditScoreByCustomer(Customer customer){
        return creditScoreDao.findCreditScoreByCustomer(customer);
    }

}
