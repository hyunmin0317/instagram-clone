package com.clone.instagram.web.dto.user;

import lombok.*;
import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@Getter
@Data
public class UserUpdateDto {
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;

    @NotBlank(message = "전화번호를 입력해 주세요.")
    private String phone;

    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    private String title;
    private String website;
}
