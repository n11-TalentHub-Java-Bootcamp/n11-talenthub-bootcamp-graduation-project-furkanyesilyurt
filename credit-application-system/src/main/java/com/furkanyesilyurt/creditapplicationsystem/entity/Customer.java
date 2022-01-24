package com.furkanyesilyurt.creditapplicationsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer", schema = "public")
public class Customer implements Serializable {

    @Id
    @SequenceGenerator(name = "customer-generator", sequenceName = "ID_CUSTOMER_SEQ")
    @GeneratedValue(generator = "customer-generator", strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "identity_no", nullable = false)
    private String identityNo;

    @Column(name = "firstname",nullable = false)
    private String firstname;

    @Column(name = "lastname",nullable = false)
    private String lastname;

    @Column(name = "monthly_income",nullable = false)
    @Digits(integer = 19, fraction = 2)
    private BigDecimal monthlyIncome;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "birthday",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "guarantee")
    @Digits(integer = 19, fraction = 2)
    private BigDecimal guarantee;

}
