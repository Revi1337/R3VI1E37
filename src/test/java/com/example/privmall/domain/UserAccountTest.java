//package com.example.privmall.domain;
//
//import com.example.privmall.domain.enumerate.Gender;
//import com.example.privmall.domain.enumerate.RoleType;
//import org.junit.jupiter.api.Test;
//
//
//class UserAccountTest {
//
//    @Test
//    public void test() {
//        UserAccount userAccount = UserAccount.create()
//                .email("test@.com")
//                .password("1234")
//                .nickname("test")
//                .phone("phoneNum")
//                .gender(Gender.MALE)
//                .build();
//
//        System.out.println(userAccount);
//        System.out.println(userAccount.getAuthorities());
//        userAccount.addAuthorities(RoleType.USER);
//        System.out.println(userAccount.getAuthorities());
//    }
//}