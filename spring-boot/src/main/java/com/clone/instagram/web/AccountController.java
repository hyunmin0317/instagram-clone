package com.clone.instagram.web;

import com.clone.instagram.service.posts.UserService;
import com.clone.instagram.web.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AccountController {
    @Autowired
    UserService userService;

    @PostMapping("/user/signup")
    public String save(UserLoginDto userLoginDto) {
        if (userService.save(userLoginDto))
            return "redirect:/";
        else
            return "redirect:/signup?error";
    }
}
