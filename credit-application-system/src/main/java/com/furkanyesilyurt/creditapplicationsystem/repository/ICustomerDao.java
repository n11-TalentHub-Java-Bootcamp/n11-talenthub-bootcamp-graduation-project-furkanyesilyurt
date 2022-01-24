package com.furkanyesilyurt.creditapplicationsystem.repository;

import com.furkanyesilyurt.creditapplicationsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ICustomerDao extends JpaRepository<Customer, Long> {

    Boolean existsByIdentityNo(String identityNo);
    Customer findCustomerByIdentityNo(String identityNo);
    void deleteByIdentityNo(String identityNo);

    Boolean existsCustomerByIdentityNoAndBirthday(String identityNo, Date birthday);
    Customer findCustomerByIdentityNoAndBirthday(String identityNo, Date birthday);

}
