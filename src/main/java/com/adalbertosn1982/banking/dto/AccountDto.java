package com.adalbertosn1982.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private Double balance;
}
