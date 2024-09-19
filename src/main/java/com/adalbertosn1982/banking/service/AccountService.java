package com.adalbertosn1982.banking.service;

import com.adalbertosn1982.banking.dto.AccountDto;
import com.adalbertosn1982.banking.exception.BusinessException;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    //AccountDto deposit(Long id, double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount) throws BusinessException;
}
