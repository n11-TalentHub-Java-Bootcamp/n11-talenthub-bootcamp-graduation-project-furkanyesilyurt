package com.furkanyesilyurt.creditapplicationsystem.repository;

import com.furkanyesilyurt.creditapplicationsystem.entity.Customer;
import com.furkanyesilyurt.creditapplicationsystem.entity.GivenCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGivenCreditDao extends JpaRepository<GivenCredit, Long> {

    Boolean existsGivenCreditByCustomer(Customer customer);
    List<GivenCredit> findGivenCreditByCustomer(Customer customer);

}
