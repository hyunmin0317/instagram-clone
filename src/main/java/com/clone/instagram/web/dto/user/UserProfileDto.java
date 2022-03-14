package com.clone.instagram.web.dto.user;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class UserProfileDto {
    private long loginId;
    private boolean loginUser;
    private boolean follow;
    private UserDto userDto;
    private int userFollowerCount;
    private int userFollowingCount;
}