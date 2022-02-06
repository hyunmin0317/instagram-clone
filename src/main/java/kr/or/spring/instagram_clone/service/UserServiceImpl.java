package kr.or.spring.instagram_clone.service;


import kr.or.spring.instagram_clone.dao.*;
import kr.or.spring.instagram_clone.dto.*;
import kr.or.spring.instagram_clone.service.security.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
    // 생성자에 위해 주입되는 객체이고, 해당 객체를 초기화할 필요가 이후에 없기 때문에 final로 선언하였다.
    // final로 선언하고 초기화를 안한 필드는 생성자에서 초기화를 해준다.
    private final UserDao userDao;
    private final UserRoleDao userRoleDao;

    // @Service가 붙은 객체는 스프링이 자동으로 Bean으로 생성하는데
    // 기본생성자가 없고 아래와 같이 인자를 받는 생성자만 있을 경우 자동으로 관련된 타입이 Bean으로 있을 경우 주입해서 사용하게 된다.
    public UserServiceImpl(UserDao userDao, UserRoleDao userRoleDao) {
        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
    }
    
    @Override
    @Transactional
    public UserEntity getUser(String loginUserId) {
        User user = userDao.getUserByEmail(loginUserId);
        return new UserEntity(user.getEmail(), user.getPassword());
    }

    @Override
    @Transactional
    public List<UserRoleEntity> getUserRoles(String loginUserId) {
        List<UserRole> userRoles = userRoleDao.getRolesByEmail(loginUserId);
        List<UserRoleEntity> list = new ArrayList<>();

        for(UserRole userRole : userRoles) {
            list.add(new UserRoleEntity(loginUserId, userRole.getRoleName()));
        }
        return list;
    }

	@Override
	@Transactional(readOnly = false)
	public void addUser(User user, boolean admin) {
		userDao.addUser(user);
		
		User selectedUser = userDao.getUserByEmail(user.getEmail());
		int userId = selectedUser.getId();
		if(admin) {
			userRoleDao.addAdminRole(userId);
		}
		userRoleDao.addUserRole(userId);
	}

	@Override
	public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
	}
}