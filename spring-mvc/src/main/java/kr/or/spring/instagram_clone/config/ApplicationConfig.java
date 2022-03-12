package kr.or.spring.instagram_clone.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "kr.or.spring.instagram_clone.dao",  "kr.or.spring.instagram_clone.service"})
@Import({ DBConfig.class })
public class ApplicationConfig {

}