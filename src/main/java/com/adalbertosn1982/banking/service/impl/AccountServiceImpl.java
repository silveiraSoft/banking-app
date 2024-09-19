package com.adalbertosn1982.banking.service.impl;

import com.adalbertosn1982.banking.dto.AccountDto;
import com.adalbertosn1982.banking.entity.Account;
//import com.adalbertosn1982.banking.exception.ResourceNotFoundException;
import com.adalbertosn1982.banking.exception.BusinessException;
import com.adalbertosn1982.banking.exception.ErrorCodes;
import com.adalbertosn1982.banking.exception.ResourceNotFoundException;
import com.adalbertosn1982.banking.mapper.AccountMapper;
import com.adalbertosn1982.banking.repository.AccountRepository;
import com.adalbertosn1982.banking.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account accountsaved = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(accountsaved);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found with id:" + id));
                //.orElseThrow(() -> new ResourceNotFoundException("Account not found with id:" + id));
                //.orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id){
        this.getAccountById(id);
        /*
        accountRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id:" + id));
                //.orElseThrow(() -> new RuntimeException("Account not found"));
         */
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        AccountDto accountDto= this.getAccountById(id);
        accountDto.setBalance(accountDto.getBalance() + amount);
        return createAccount(accountDto);
        /*
        Account account = accountRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Account not found with id:" + id)
        );
        account.setBalance(account.getBalance() + amount);
        Account accountsaved = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(accountsaved);
         */
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        AccountDto accountDto= this.getAccountById(id);
        Double balance = accountDto.getBalance();
        if(balance < amount) {
            //throw new BusinessException("Insufficient funds");
            throw new BusinessException(ErrorCodes.ACCOUNT_INSUFFICIENT_FUND, "Available fund:" + balance);
        }
        accountDto.setBalance(balance - amount);
        return createAccount(accountDto);
    }

}