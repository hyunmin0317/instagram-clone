package com.clone.instagram.web.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class UserLoginDto {
    private String name;
    private String password;
    private String username;
    private String email;
}
