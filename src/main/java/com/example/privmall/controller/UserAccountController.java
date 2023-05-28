package com.example.privmall.controller;

import com.example.privmall.dto.request.principal.UserAccountPrincipal;
import com.example.privmall.dto.request.SearchCondition;
import com.example.privmall.dto.request.JoinRequest;
import com.example.privmall.dto.response.UserAccountResponse;
import com.example.privmall.service.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController @RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping(path = "/api/user/join")
    public ResponseEntity<Void> createUserAccount(
            @RequestBody @Valid JoinRequest joinRequest) {
        userAccountService.createUserAccount(joinRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/api/user/{userId}")
    public ResponseEntity<UserAccountResponse> searchUserAccount(
            @PathVariable(name = "userId") Long userId,
            @AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal) {
        System.out.println("userAccountPrincipal = " + userAccountPrincipal);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userAccountService.searchUserAccount(userId));
    }

    @GetMapping(path = "/api/users")
    public ResponseEntity<List<UserAccountResponse>> searchAllUserAccount(
            @Valid SearchCondition searchCondition) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userAccountService.searchAllUserAccount(searchCondition));
    }
    
}

