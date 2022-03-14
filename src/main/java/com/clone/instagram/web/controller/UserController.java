package com.clone.instagram.web.controller;

import com.clone.instagram.service.UserService;
import com.clone.instagram.web.dto.user.UserDto;
import com.clone.instagram.web.dto.user.UserProfileDto;
import com.clone.instagram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping({"/", "user/story"})
    public String story(Authentication authentication, Model model) {
        UserDto userDto = userService.getUserDtoByEmail(authentication.getName());
        model.addAttribute("userDto", userDto);
        return "user/story";
    }

    @GetMapping("/user/profile")
    public String profile(Model model, @RequestParam long id, Authentication authentication) {
        UserProfileDto userProfileDto = userService.getProfile(id, authentication.getName());
        model.addAttribute("userProfileDto", userProfileDto);
        return "user/profile";
    }

    @GetMapping("/user/update")
    public String update(Authentication authentication, Model model) {
        UserDto userDto = userService.getUserDtoByEmail(authentication.getName());
        model.addAttribute("userDto", userDto);
        return "user/update";
    }

    @PostMapping("/user/update")
    public String updateUser(UserUpdateDto userUpdateDto, RedirectAttributes redirectAttributes) {
        userService.update(userUpdateDto);
        redirectAttributes.addAttribute("id", userUpdateDto.getId());
        return "redirect:/user/story";
    }

}
