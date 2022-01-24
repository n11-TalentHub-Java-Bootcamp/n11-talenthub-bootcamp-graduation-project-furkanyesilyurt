package com.furkanyesilyurt.creditapplicationsystem.repository;

import com.furkanyesilyurt.creditapplicationsystem.entity.CreditScore;
import com.furkanyesilyurt.creditapplicationsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICreditScoreDao extends JpaRepository<CreditScore, Long> {

    void deleteByCustomer(Customer customer);
    CreditScore findCreditScoreByCustomer(Customer customer);

}
