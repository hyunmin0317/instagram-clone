package com.clone.instagram.web.controller;

import com.clone.instagram.service.UserService;
import com.clone.instagram.web.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    //메인 sroty화면으로 이동
    @GetMapping("user/story")
    public String story(Authentication authentication, Model model) {
        UserDto userDto = userService.getUserDtoByEmail(authentication.getName());
        model.addAttribute("userDto", userDto);
        return "user/story";
    }
}
