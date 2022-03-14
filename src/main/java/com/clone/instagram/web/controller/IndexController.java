package com.clone.instagram.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class IndexController {

    // 회원 가입 폼으로 이동
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // 로그인 화면으로 이동
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 메인 화면으로 이동
    @GetMapping({"/", "post/story"})
    public String story() {
        return "post/story";
    }
}
