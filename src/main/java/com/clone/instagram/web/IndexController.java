package com.clone.instagram.web;

import lombok.RequiredArgsConstructor;
import com.clone.instagram.config.auth.LoginUser;
import com.clone.instagram.config.auth.dto.SessionUser;
import com.clone.instagram.service.posts.PostsService;
import com.clone.instagram.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;


    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // @LoginUser 를 사용하여 세션 정보를 갖고 옴
    @GetMapping("/main")
    public String index(Model model, @LoginUser SessionUser user) {  // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
