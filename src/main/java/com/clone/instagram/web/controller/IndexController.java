package com.clone.instagram.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/signup") //회원 가입 폼으로 이동
    public String signup() {
        return "signup";
    }

    @GetMapping("/login") //로그인 화면으로 이동
    public String login() {
        return "login";
    }

    //메인 sroty화면으로 이동
    @GetMapping({"/", "post/story"})
    public String story() {
        return "post/story";
    }
}
