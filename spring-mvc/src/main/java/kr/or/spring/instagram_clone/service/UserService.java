package kr.or.spring.instagram_clone.service;


import kr.or.spring.instagram_clone.dto.User;
import kr.or.spring.instagram_clone.service.security.UserDbService;

public interface UserService extends UserDbService {

	void addUser(User user, boolean admin);

	User getUserByEmail(String loginId);

}