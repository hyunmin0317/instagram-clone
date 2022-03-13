package com.clone.instagram.web;

import com.clone.instagram.config.auth.LoginUser;
import com.clone.instagram.config.auth.dto.SessionUser;
import com.clone.instagram.domain.user.User;
import com.clone.instagram.service.posts.UserService;
import lombok.RequiredArgsConstructor;
import com.clone.instagram.service.posts.PostsService;
import com.clone.instagram.web.dto.PostsResponseDto;
import com.clone.instagram.web.dto.PostsSaveRequestDto;
import com.clone.instagram.web.dto.PostsUpdateRequestDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor    // 모든 final 필드가 포함된 생성자 생성
@RestController
public class PostsApiController {
    private final UserService userService;
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@LoginUser SessionUser user, @RequestBody PostsSaveRequestDto requestDto) {
        requestDto.setAuthor(userService.getUser(user.getEmail()));
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
