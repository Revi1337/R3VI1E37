package com.example.privmall.controller;

import com.example.privmall.domain.UserAccount;
import com.example.privmall.domain.enumerate.Gender;
import com.example.privmall.domain.enumerate.Host;
import com.example.privmall.dto.request.JoinRequest;
import com.example.privmall.repository.UserAccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest @AutoConfigureMockMvc
class UserAccountControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final MockMvc mockMvc;

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountControllerTest(MockMvc mockMvc, UserAccountRepository userAccountRepository) {
        this.mockMvc = mockMvc;
        this.userAccountRepository = userAccountRepository;
    }

    @BeforeEach
    public void clearDataBase() {
        userAccountRepository.deleteAll();
    }

    @Test
    @DisplayName(value = "회원가입이 정상적으로 진행되어야 하고 201 을 반환해야 한다.")
    public void joinUserAccount() throws Exception {
        JoinRequest joinRequest = JoinRequest
                .of("revi1337@naver.com", "revi1337", "1234", "010-3551-1998", Gender.MALE, Host.GITHUB);
        String joinRequestJson = objectMapper.writeValueAsString(joinRequest);

        mockMvc.perform(post("/api/user/join")
                        .contentType(APPLICATION_JSON)
                        .content(joinRequestJson))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName(value = "@NotEmpty 필드 값을 넣지 않으면 회원가입 시 400 Exception 이 터져야 한다.")
    public void occurredExceptionWhenNonSpecifyNotEmptyField() throws Exception {
        JoinRequest joinRequest = JoinRequest
                .of("", "revi1337", "1234", "010-3551-1998", Gender.MALE, Host.GITHUB);
        String joinRequestJson = objectMapper.writeValueAsString(joinRequest);

        mockMvc.perform(post("/api/user/join")
                        .contentType(APPLICATION_JSON)
                        .content(joinRequestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("Invalid Request"))
                .andExpect(jsonPath("$.validation.email").value("email must be specified"))
                .andDo(print());
    }

    @Test
    @DisplayName(value = "회원가입 시 동일한 이메일이 존재하면 400 익셉션이 터져야 한다.")
    public void occurredExceptionWhenJoinUserAccountHavingSameInformation() throws Exception {
        JoinRequest joinRequest = JoinRequest.of("revi1337@naver.com", "dummy", "1234", "010-3551-1998", Gender.FEMALE, Host.GITHUB);
        UserAccount userAccount = joinRequest.toDto().toEntity();
        userAccountRepository.save(userAccount);
        String joinRequestJson = objectMapper.writeValueAsString(joinRequest);

        mockMvc.perform(post("/api/user/join")
                        .contentType(APPLICATION_JSON)
                        .content(joinRequestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("An account registered with revi1337@naver.com already exists"))
                .andDo(print());
    }

    @Test
    @DisplayName(value = "모든 회원을 찾는 요청이 들어오면 모든 회원을 반환해야한다.")
    public void returnAllUserAccountWhenInComingSearchAllUserAccountRequest() throws Exception {
        UserAccount userAccount1 = JoinRequest
                .of("revi1337@naver.com", "dummy", "1234", "010-3551-1998", Gender.FEMALE, Host.GITHUB)
                .toDto().toEntity();
        UserAccount userAccount2 = JoinRequest
                .of("revi1338@naver.com", "dummy", "1234", "010-3551-1998", Gender.FEMALE, Host.GITHUB)
                .toDto().toEntity();
        UserAccount userAccount3 = JoinRequest
                .of("revi1339@naver.com", "dummy", "1234", "010-3551-1998", Gender.FEMALE, Host.GITHUB)
                .toDto().toEntity();
        userAccountRepository.saveAll(List.of(userAccount1, userAccount2, userAccount3));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].email").value("revi1337@naver.com"))
                .andExpect(jsonPath("$[1].email").value("revi1338@naver.com"))
                .andExpect(jsonPath("$[2].email").value("revi1339@naver.com"))
                .andDo(print());
    }


    @Test
    @DisplayName(value = "모든 회원을 찾는 요청이 들어오면 모든 회원을 반환해야한다. (페이징 쿼리를 사용)")
    public void returnSeveralUserAccountWhenRequestUserAccountPagingRequest() throws Exception {
        for (int i = 1; i < 30; i++) {
            UserAccount userAccount = JoinRequest
                    .of(i + "@", "dummy", "1234", "010-3551-1998", Gender.FEMALE, Host.GITHUB)
                    .toDto().toEntity();
            userAccountRepository.save(userAccount);
        }

        mockMvc.perform(get("/api/users")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].email").value("1@"))
                .andExpect(jsonPath("$[1].email").value("2@"))
                .andDo(print());
    }

}