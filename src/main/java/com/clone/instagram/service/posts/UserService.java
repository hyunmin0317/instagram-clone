package com.clone.instagram.service.posts;

import com.clone.instagram.domain.user.Role;
import com.clone.instagram.domain.user.User;
import com.clone.instagram.domain.user.UserRepository;
import com.clone.instagram.web.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String email) {
        return userRepository.getByEmail(email);
    }

    @Transactional
    public boolean save(UserLoginDto userLoginDto) {
        if (userRepository.getByEmail(userLoginDto.getEmail()) != null)
            return false;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userRepository.save(User.builder()
                .name(userLoginDto.getName())
                .username(userLoginDto.getUsername())
                .email(userLoginDto.getEmail())
                .picture(null)
                .role(Role.USER)
                .build());
        return true;
    }

}
