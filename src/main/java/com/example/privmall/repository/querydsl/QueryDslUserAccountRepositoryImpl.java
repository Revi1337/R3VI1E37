package com.example.privmall.repository.querydsl;

import com.example.privmall.domain.QUserAccount;
import com.example.privmall.domain.UserAccount;
import com.example.privmall.dto.request.SearchCondition;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class QueryDslUserAccountRepositoryImpl implements QueryDslUserAccountRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserAccount> searchAllUserAccount(SearchCondition searchCondition) {
        return jpaQueryFactory
                .selectFrom(QUserAccount.userAccount)
                .offset(searchCondition.getOffset())
                .limit(searchCondition.size())
                .fetch();
    }

}
