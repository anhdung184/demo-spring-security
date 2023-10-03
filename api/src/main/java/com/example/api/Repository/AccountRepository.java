package com.example.api.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.Model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findByaccusername(String accusername);
    Account findByaccpassword(String accpassword);
    Account findById(long Id);
}