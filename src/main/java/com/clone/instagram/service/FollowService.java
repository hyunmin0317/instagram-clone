package com.clone.instagram.service;

import com.clone.instagram.domain.follow.Follow;
import com.clone.instagram.domain.follow.FollowRepository;
import com.clone.instagram.domain.user.User;
import com.clone.instagram.domain.user.UserRepository;
import com.clone.instagram.web.dto.follow.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.qlrm.mapper.JpaResultMapper;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final EntityManager em;

    @Transactional
    public long getFollowIdByFromEmailToId(String email, Long toId) {
        User fromUser = userRepository.findUserByEmail(email);
        User toUser = userRepository.findUserById(toId);

        Follow follow = followRepository.findFollowByFromUserAndToUser(fromUser, toUser);

        if(follow != null) return follow.getId();
        else return -1;
    }

    @Transactional
    public Follow save(String email, Long toUserId) {
        User fromUser = userRepository.findUserByEmail(email);
        User toUser = userRepository.findUserById(toUserId);

        return followRepository.save(Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build());
    }

    public List<FollowDto> getFollowDtoListByProfileIdAboutFollower(long profileId, String loginEmail) {
        long loginId = userRepository.findUserByEmail(loginEmail).getId();

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.name, u.profile_img_url, ");
        sb.append("if ((SELECT 1 FROM follow WHERE to_user_id = ? AND from_user_id = u.id), TRUE, FALSE) AS followState, ");
        sb.append("if ((?=u.id), TRUE, FALSE) AS loginUser ");
        sb.append("FROM user u, follow f ");
        sb.append("WHERE u.id = f.from_user_id AND f.to_user_id = ?");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, loginId)
                .setParameter(2, loginId)
                .setParameter(3, profileId);

        JpaResultMapper result = new JpaResultMapper();
        List<FollowDto> followDtoList = result.list(query, FollowDto.class);
        return followDtoList;
    }

    public List<FollowDto> getFollowDtoListByProfileIdAboutFollowing(long profileId, String loginEmail) {
        long loginId = userRepository.findUserByEmail(loginEmail).getId();

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.name, u.profile_img_url, ");
        sb.append("if ((SELECT 1 FROM follow WHERE from_user_id = ? AND to_user_id = u.id), TRUE, FALSE) AS followState, ");
        sb.append("if ((?=u.id), TRUE, FALSE) AS loginUser ");
        sb.append("FROM user u, follow f ");
        sb.append("WHERE u.id = f.to_user_id AND f.from_user_id = ?");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, loginId)
                .setParameter(2, loginId)
                .setParameter(3, profileId);

        JpaResultMapper result = new JpaResultMapper();
        List<FollowDto> followDtoList = result.list(query, FollowDto.class);
        return followDtoList;
    }
}