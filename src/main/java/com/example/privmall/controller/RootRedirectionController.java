package com.example.privmall.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootRedirectionController implements ErrorController {

    @GetMapping(path = {"/error", "/"})
    public String forwardRoot() {
        return "/index.html";
    }
}
