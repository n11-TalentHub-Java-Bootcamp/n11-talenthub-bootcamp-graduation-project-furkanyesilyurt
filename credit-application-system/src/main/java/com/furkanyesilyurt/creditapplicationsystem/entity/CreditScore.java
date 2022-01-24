package com.furkanyesilyurt.creditapplicationsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credit_score", schema = "public")
public class CreditScore implements Serializable {

    @Id
    @SequenceGenerator(name = "credit-score-generator", sequenceName = "ID_CREDIT_SCORE_SEQ")
    @GeneratedValue(generator = "credit-score-generator", strategy = GenerationType.AUTO)
    @Column(name = "score_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "score_customer_id_fkey"), referencedColumnName = "customer_id")
    private Customer customer;

    @Column(name = "credit_score", nullable = false)
    private Long creditScore;

}
