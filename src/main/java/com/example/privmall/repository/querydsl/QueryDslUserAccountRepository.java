package com.example.privmall.repository.querydsl;

import com.example.privmall.domain.UserAccount;
import com.example.privmall.dto.request.SearchCondition;

import java.util.List;

public interface QueryDslUserAccountRepository {


    List<UserAccount> searchAllUserAccount(SearchCondition searchCondition);
}
