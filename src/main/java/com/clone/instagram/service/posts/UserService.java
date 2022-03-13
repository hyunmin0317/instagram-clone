package com.clone.instagram.service.posts;

import com.clone.instagram.domain.user.User;
import com.clone.instagram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String email) {
        return userRepository.getByEmail(email);
    }

}
