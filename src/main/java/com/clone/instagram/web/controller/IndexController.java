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
        List<UserProfileDto> users = new ArrayList();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails user = (PrincipalDetails) authentication.getPrincipal();
        int i=0;
        long id = user.getUser().getId();
        UserProfileDto mainuser = null;

        for (User u: userService.getUsers()) {
            UserProfileDto userProfileDto = userService.getUserProfileDto(u.getId(), id);
            if (id!=userProfileDto.getUser().getId())
                if (i<=4)
                    users.add(userProfileDto);
            else
                mainuser = userProfileDto;
            i++;
        }

        model.addAttribute("users", users);
        model.addAttribute("mainuser", mainuser);
        return "post/home";
    }
}
