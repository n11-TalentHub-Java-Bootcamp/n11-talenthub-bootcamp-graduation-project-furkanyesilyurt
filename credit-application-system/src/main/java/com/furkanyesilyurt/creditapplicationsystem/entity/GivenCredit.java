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
@Table(name = "given_credit", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","customer", "creditScore"})
public class GivenCredit implements Serializable {

    @Id
    @SequenceGenerator(name = "given-credit-generator", sequenceName = "ID_GIVEN_CREDIT_SEQ")
    @GeneratedValue(generator = "given-credit-generator", strategy = GenerationType.AUTO)
    @Column(name = "credit_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "credit_customer_id_fkey"), referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "score_id", foreignKey = @ForeignKey(name = "credit_score_id_fkey"), referencedColumnName = "score_id")
    private CreditScore creditScore;

    @Column(name = "credit_limit", nullable = false)
    private BigDecimal creditLimit;

}
