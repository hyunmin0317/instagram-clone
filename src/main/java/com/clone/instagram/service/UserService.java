package com.clone.instagram.service;

import com.clone.instagram.domain.user.User;
import com.clone.instagram.domain.user.UserRepository;
import com.clone.instagram.web.dto.user.UserSignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    public boolean save(UserSignupDto userSignupDto) {
        if(userRepository.findUserByEmail(userSignupDto.getEmail()) != null)
            return false;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userRepository.save(User.builder()
                .email(userSignupDto.getEmail())
                .password(encoder.encode(userSignupDto.getPassword()))
                .phone(userSignupDto.getPhone())
                .name(userSignupDto.getName())
                .title(null)
                .website(null)
                .profileImgUrl("/img/default_profile.jpg")
                .build());
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email);
    }
}
