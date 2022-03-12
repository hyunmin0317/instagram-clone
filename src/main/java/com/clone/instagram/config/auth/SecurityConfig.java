package com.clone.instagram.config.auth;

import lombok.RequiredArgsConstructor;
import com.clone.instagram.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity          // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                    .authorizeRequests()    // URL 권한 권리를 설정하는 옵션의 시작점
                    // 권한 관리 대상을 지정하는 옵션
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    // 로그아웃 성공 시 / 주소로 이동
                    .logout().logoutSuccessUrl("/")
                .and()
                    .oauth2Login()      // OAuth 2 로그인 기능에 대한 설정의 진입점
                    .loginPage("/")
                    .defaultSuccessUrl("/main")
                    .failureUrl("/")
                    .userInfoEndpoint() // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정 담당
                    // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                    .userService(customOAuth2UserService); 
    }

}
