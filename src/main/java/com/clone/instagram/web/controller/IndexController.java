package com.clone.instagram.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping("/")
    public String story() {
        return "post/home";
    }
}
