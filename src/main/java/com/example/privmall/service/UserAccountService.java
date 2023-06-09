package com.example.privmall.service;

import com.example.privmall.domain.enumerate.RoleType;
import com.example.privmall.dto.UserAccountDto;
import com.example.privmall.dto.request.SearchCondition;
import com.example.privmall.dto.request.JoinRequest;
import com.example.privmall.dto.response.UserAccountResponse;
import com.example.privmall.exception.DuplicateEmailException;
import com.example.privmall.exception.UserNotFoundException;
import com.example.privmall.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service @RequiredArgsConstructor @Transactional(readOnly = true)
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUserAccount(JoinRequest joinRequest) {
        String joinEmail = joinRequest.email();
        if (userAccountRepository.findByEmail(joinEmail).isPresent())
            throw new DuplicateEmailException(joinEmail);
        userAccountRepository.save(joinRequest
                .toDto()
                .toEntity()
                .changePassword(passwordEncoder.encode(joinRequest.password()))
                .addAuthorities(RoleType.ADMIN)       // TODO 지금은 테스트 중이니까 임시적으로 모두 ADMIN 권한을 부여 --> 나중에 제거 해주어야 함.
        );
    }

    public UserAccountResponse searchUserAccount(Long userId) {
        return userAccountRepository
                .findById(userId)
                .map(UserAccountDto::from)
                .map(UserAccountResponse::from)
                .orElseThrow(UserNotFoundException::new);
    }

    public List<UserAccountResponse> searchAllUserAccount(SearchCondition searchCondition) {
        return userAccountRepository
                .searchAllUserAccount(searchCondition)
                .stream()
                .map(UserAccountDto::from)
                .map(UserAccountResponse::from)
                .toList();
    }


}
