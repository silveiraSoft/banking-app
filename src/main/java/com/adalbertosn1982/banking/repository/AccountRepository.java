package com.adalbertosn1982.banking.repository;

import com.adalbertosn1982.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
}
