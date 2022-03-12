package kr.or.spring.instagram_clone.service.security;

import java.util.List;
import kr.or.spring.instagram_clone.dto.*;

public interface UserDbService {
	public UserEntity getUser(String loginUserId);

	public List<UserRoleEntity> getUserRoles(String loginUserId);
}