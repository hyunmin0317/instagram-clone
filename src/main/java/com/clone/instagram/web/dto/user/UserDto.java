package com.clone.instagram.web.dto.user;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class UserDto {

    private long id;
    private String email;
    private String phone;
    private String name;
    private String title;
    private String website;
    private String profileImgUrl;
}