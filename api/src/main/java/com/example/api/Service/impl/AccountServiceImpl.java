package com.example.api.Service.impl;

import com.example.api.Model.Account;
import com.example.api.Model.MyUserDetails;
import com.example.api.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class AccountServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String accusername) throws UsernameNotFoundException {
        Account account = accountRepository.findByaccusername(accusername);
        if (account == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(account);
    }

    public UserDetails loadUserById(long Id) throws UsernameNotFoundException {
        Account account = accountRepository.findById(Id);
        if (account == null) {
            throw new UsernameNotFoundException("Could not find Id");
        }
        return new MyUserDetails(account);
    }
}
