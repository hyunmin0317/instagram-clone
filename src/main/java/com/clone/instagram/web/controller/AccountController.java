package com.clone.instagram.web.controller;

import com.clone.instagram.service.UserService;
import com.clone.instagram.web.dto.user.UserSignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class AccountController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public String signup(UserSignupDto userSignupDto, BindingResult bindingResult) {
        if (userService.save(userSignupDto))
            return "redirect:/login";
        else
            return "redirect:/signup?error";
    }
}
