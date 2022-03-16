package com.clone.instagram.web.controller;

import com.clone.instagram.domain.user.User;
import lombok.RequiredArgsConstructor;
import com.clone.instagram.config.auth.PrincipalDetails;
import com.clone.instagram.service.UserService;
import com.clone.instagram.web.dto.user.UserProfileDto;
import com.clone.instagram.web.dto.user.UserUpdateDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    // 사용자 프로필 화면으로 이동
    @GetMapping("/user/profile")
    public String profile(Model model, @RequestParam long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserProfileDto userProfileDto = userService.getUserProfileDto(id, principalDetails.getUser().getId());
        model.addAttribute("userProfileDto", userProfileDto);
        return "user/profile";
    }

    // 사용자 정보 수정 페이지로 이동
    @GetMapping("/user/update")
    public String update() {
        return "user/update";
    }

    // 사용자 정보 업데이트
    @PostMapping("/user/update")
    public String updateUser(@Valid UserUpdateDto userUpdateDto, BindingResult bindingResult, @RequestParam("profileImgUrl") MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.update(userUpdateDto, multipartFile, principalDetails);
        redirectAttributes.addAttribute("id", principalDetails.getUser().getId());
        return "redirect:/user/profile";
    }

    // 유저 팔로우 페이지로 이동
    @GetMapping("/user/follow")
    public String followPage(Model model) {
        List<UserProfileDto> users = new ArrayList();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails user = (PrincipalDetails) authentication.getPrincipal();
        long id = user.getUser().getId();

        for (User u: userService.getUsers()) {
            UserProfileDto userProfileDto = userService.getUserProfileDto(u.getId(), id);
            if (id!=userProfileDto.getUser().getId())
                users.add(userProfileDto);
        }

        model.addAttribute("users", users);
        return "user/follow";
    }
}
