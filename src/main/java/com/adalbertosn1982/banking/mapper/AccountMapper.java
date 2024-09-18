package com.adalbertosn1982.banking.mapper;

import com.adalbertosn1982.banking.dto.AccountDto;
import com.adalbertosn1982.banking.entity.Account;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
    }

    public static Account mapToAccount(AccountDto accountDto) {
        return  new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
    }
}
