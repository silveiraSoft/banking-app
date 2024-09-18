package com.adalbertosn1982.banking.service.impl;

import com.adalbertosn1982.banking.dto.AccountDto;
import com.adalbertosn1982.banking.entity.Account;
import com.adalbertosn1982.banking.mapper.AccountMapper;
import com.adalbertosn1982.banking.repository.AccountRepository;
import com.adalbertosn1982.banking.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        //return null;
        Account account = AccountMapper.mapToAccount(accountDto);
        Account  accountsaved = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(accountsaved);
    }
}
