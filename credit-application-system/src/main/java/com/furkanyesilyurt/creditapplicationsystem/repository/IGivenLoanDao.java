package com.furkanyesilyurt.creditapplicationsystem.repository;

import com.furkanyesilyurt.creditapplicationsystem.entity.Customer;
import com.furkanyesilyurt.creditapplicationsystem.entity.GivenLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGivenLoanDao extends JpaRepository<GivenLoan, Long> {

    Boolean existsGivenLoanByCustomer(Customer customer);
    List<GivenLoan> findGivenLoanByCustomer(Customer customer);

}
