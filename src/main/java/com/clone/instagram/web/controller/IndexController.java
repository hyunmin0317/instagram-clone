package com.clone.instagram.web.controller;

import com.clone.instagram.config.auth.PrincipalDetails;
import com.clone.instagram.domain.user.User;
import com.clone.instagram.service.UserService;
import com.clone.instagram.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final UserService userService;

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
    public String main(Model model) {
        List<UserProfileDto> list = new ArrayList();
        List<UserProfileDto> users = new ArrayList();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails user = (PrincipalDetails) authentication.getPrincipal();
        long id = user.getUser().getId();
        int i = 0;

        for (User u: userService.getUsers()) {
            UserProfileDto userProfileDto = userService.getUserProfileDto(u.getId(), id);
            if (id!=userProfileDto.getUser().getId())
                list.add(userProfileDto);
            else
                model.addAttribute("mainuser", userProfileDto);
        }

        for (UserProfileDto u: list) {
            users.add(u);
            if (i++==4)
                break;
        }

        model.addAttribute("users", users);
        return "post/home";
    }
}
