package com.furkanyesilyurt.creditapplicationsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "given_loan", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","customer", "creditScore"})
public class GivenLoan implements Serializable {

    @Id
    @SequenceGenerator(name = "given-loan-generator", sequenceName = "ID_GIVEN_LOAN_SEQ")
    @GeneratedValue(generator = "given-loan-generator", strategy = GenerationType.AUTO)
    @Column(name = "credit_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "loan_customer_id_fkey"), referencedColumnName = "customer_id")
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "score_id", foreignKey = @ForeignKey(name = "loan_score_id_fkey"), referencedColumnName = "score_id")
    private CreditScore creditScore;

    @Column(name = "credit_limit", nullable = false)
    private BigDecimal creditLimit;

}
