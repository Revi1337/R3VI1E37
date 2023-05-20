package com.example.privmall.repository;

import com.example.privmall.domain.UserAccount;
import com.example.privmall.repository.querydsl.QueryDslUserAccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserAccountRepository extends
        JpaRepository<UserAccount, Long>, QueryDslUserAccountRepository {

    Optional<UserAccount> findByEmail(String email);
}
