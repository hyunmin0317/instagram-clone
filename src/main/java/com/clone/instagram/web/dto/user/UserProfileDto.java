package com.clone.instagram.web.dto.user;

import lombok.*;
import com.clone.instagram.domain.user.User;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class UserProfileDto {
    private boolean loginUser;
    private boolean follow;
    private User user;
    private int postCount;
    private int userFollowerCount;
    private int userFollowingCount;
}
