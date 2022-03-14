package com.clone.instagram.web.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
@Setter
@Data
public class UserSignupDto {
    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다")
    private String email;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;

    @NotBlank(message = "전화번호를 입력해 주세요.")
    private String phone;

    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;
}
