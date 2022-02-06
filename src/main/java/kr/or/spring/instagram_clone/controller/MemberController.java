package kr.or.spring.instagram_clone.controller;


import java.security.Principal;

import kr.or.spring.instagram_clone.dto.*;
import kr.or.spring.instagram_clone.service.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.spring.instagram_clone.service.UserService;

@Controller
@RequestMapping(path = "/")
public class MemberController {
    // 스프링 컨테이너가 생성자를 통해 자동으로 주입한다.
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public MemberController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/loginform")
    public String loginform(){
        return "/loginform";
    }

    @GetMapping("/loginerror")
    public String loginerror(@RequestParam("login_error")String loginError){
        return "/loginerror";
    }

    @GetMapping("/joinform")
    public String joinform(){
        return "/joinform";
    }

    // 사용자가 입력한 name, email, password가 member에 저장된다.
    @PostMapping("/join")
    public String join(@ModelAttribute User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user, false);
        return "redirect:/list";
    }

    @GetMapping("/userinfo")
    public String memberInfo(Principal principal, ModelMap modelMap){
        String loginId = principal.getName();
        User user = userService.getUserByEmail(loginId);
        modelMap.addAttribute("member", user);
        return "/userinfo";
    }
}