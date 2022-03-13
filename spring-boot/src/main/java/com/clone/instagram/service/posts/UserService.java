package com.clone.instagram.service.posts;

import com.clone.instagram.domain.user.Role;
import com.clone.instagram.domain.user.User;
import com.clone.instagram.domain.user.UserRepository;
import com.clone.instagram.web.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
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
                .username(encoder.encode(userLoginDto.getUsername()))
                .email(userLoginDto.getEmail())
                .password(userLoginDto.getPassword())
                .picture(null)
                .role(Role.USER)
                .build());
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        return user;
    }
}
