package com.clone.instagram.config;

import com.clone.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@EnableWebSecurity          // Spring Security 설정 활성화
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/login", "/signup", "/style/**", "/js/**", "/img/**", "/h2-console/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/loginForm")
                    .defaultSuccessUrl("/user/story")
                .and()
                   .logout()
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true); // 세션 전체 삭제
    }
}
