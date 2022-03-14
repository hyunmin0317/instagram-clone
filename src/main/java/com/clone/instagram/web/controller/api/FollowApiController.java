package com.clone.instagram.web.controller.api;

import com.clone.instagram.domain.follow.Follow;
import com.clone.instagram.domain.follow.FollowRepository;
import com.clone.instagram.service.FollowService;
import com.clone.instagram.web.dto.follow.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FollowApiController {

    private final FollowRepository followRepository;
    private final FollowService followService;

    @PostMapping("/follow/{toUserId}")
    public Follow followUser(@PathVariable long toUserId, Authentication authentication) {
        return followService.save(authentication.getName(), toUserId);
    }

    @DeleteMapping("/follow/{toUserId}")
    public void unFollowUser(@PathVariable long toUserId, Authentication authentication) {
        Long id = followService.getFollowIdByFromEmailToId(authentication.getName(), toUserId);
        followRepository.deleteById(id);
    }

    @GetMapping("/follow/{profileId}/follower")
    public List<FollowDto> getFollower(@PathVariable long profileId, Authentication authentication) {
        return followService.getFollowDtoListByProfileIdAboutFollower(profileId, authentication.getName());
    }

    @GetMapping("/follow/{profileId}/following")
    public List<FollowDto> getFollowing(@PathVariable long profileId, Authentication authentication) {
        return followService.getFollowDtoListByProfileIdAboutFollowing(profileId, authentication.getName());
    }
}