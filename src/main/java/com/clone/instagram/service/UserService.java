package com.clone.instagram.service;

import com.clone.instagram.domain.user.User;
import com.clone.instagram.domain.user.UserRepository;
import com.clone.instagram.web.dto.user.UserDto;
import com.clone.instagram.web.dto.user.UserProfileDto;
import com.clone.instagram.web.dto.user.UserSignupDto;
import com.clone.instagram.web.dto.user.UserUpdateDto;
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

    @Transactional
    public void update(UserUpdateDto userUpdateDto) {
        User user = userRepository.findUserById(userUpdateDto.getId());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.update(
                encoder.encode(userUpdateDto.getPassword()),
                userUpdateDto.getPhone(),
                userUpdateDto.getName(),
                userUpdateDto.getTitle(),
                userUpdateDto.getWebsite(),
                userUpdateDto.getProfileImgUrl()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email);
    }

    public UserDto getUserDtoByEmail(String email) {
        User user = userRepository.findUserByEmail(email);

        return UserDto.builder()
                .id(user.getId())
                .email(email)
                .name(user.getName())
                .title(user.getTitle())
                .phone(user.getPhone())
                .website(user.getWebsite())
                .profileImgUrl(user.getProfileImgUrl())
                .build();
    }

    @Transactional
    public UserProfileDto getProfile(long currentId, String loginEmail) {
        UserProfileDto userProfileDto = new UserProfileDto();

        User user = userRepository.findUserById(currentId);
        userProfileDto.setUserDto(UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .title(user.getTitle())
                .phone(user.getPhone())
                .website(user.getWebsite())
                .profileImgUrl(user.getProfileImgUrl())
                .build());

        User loginUser = userRepository.findUserByEmail(loginEmail);
        userProfileDto.setLoginUser(loginUser.getId() == user.getId());
        userProfileDto.setLoginId(loginUser.getId());

        return userProfileDto;
    }
}
