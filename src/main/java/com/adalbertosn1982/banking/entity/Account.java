package com.adalbertosn1982.banking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;


@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "account",
       indexes = {
       @Index(name = "idx_account_account_holder_name", columnList = "account_holder_name", unique = true)
    }
    /*
    ,uniqueConstraints = {
       @UniqueConstraint(name = "uc_account_account_holder_name", columnNames = {"account_holder_name"})
    }*/
    //schema = "banking"
)
@Entity
public class Account {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(columnDefinition = "varchar(100) default ''")
    @Column(name = "account_holder_name", nullable = false,  length = 100)
    private String accountHolderName;

    @Column(name = "balance", nullable = false, columnDefinition = "double default 0.00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    //@Column(name = "balance", nullable = false)
    private Double balance;

}
