package com.adalbertosn1982.banking.controller;

import com.adalbertosn1982.banking.dto.AccountDto;
import com.adalbertosn1982.banking.entity.Account;
import com.adalbertosn1982.banking.exception.BusinessException;
import com.adalbertosn1982.banking.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("accounts")
public class AccountController {
    private AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("hello")
    //@ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String hello(){
        log.info("Hello world");
        return "Hello world";
    }
    //add account rest API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
        //return ResponseEntity.ok(accountService.getAccountById(id));
    }
    /*
    @GetMapping
    public ResponseEntity<AccountDto> deposit(@RequestParam Long id, @RequestParam double amount){
        return new ResponseEntity<>(accountService.deposit(id, amount), HttpStatus.OK);
    }*/
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        //return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully!");
    }

    @PutMapping("{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestParam double amount){
        //return new ResponseEntity<>(accountService.deposit(id, amount), HttpStatus.OK);
        return ResponseEntity.ok(accountService.deposit(id, amount));
    }

    @PostMapping("{id}/deposit")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto depositV2(@PathVariable Long id, @RequestBody Map<String, Double> request){
        return accountService.deposit(id, request.get("amount"));
    }

    @PostMapping("{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        return ResponseEntity.ok(accountService.withdraw(id, request.get("amount")));
        //return new ResponseEntity<>(accountService.withdraw(id, request.get("account")), HttpStatus.OK);
    }
}
