package com.example.adambackend.service;

import com.example.adambackend.entities.Account;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> findAll();

    Account save(Account account);

    void deleteById(Long id);

    Optional<Account> findById(Long id);

    List<Account> findByRoleName(String roleName);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    void register(Account account, String siteURL) throws UnsupportedEncodingException, MessagingException;

    void sendVerificationEmail(Account account, String siteURL) throws MessagingException, UnsupportedEncodingException;

    boolean verify(String verificationCode);
    Optional<Account> findByEmail(String email);
}
