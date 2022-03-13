package com.clone.instagram.web.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class UserSignupDto {
    private String email;
    private String password;
    private String phone;
    private String name;
}
