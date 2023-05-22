package com.example.privmall.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController @RequestMapping(value = "/api/oauth2")
public class OAuth2Controller {

    @GetMapping(path = "/google")
    public void forwardGoogle(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("/oauth2/authorization/google");
    }

    @GetMapping(path = "/github")
    public void forwardGithub(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("/oauth2/authorization/github");
    }

}
