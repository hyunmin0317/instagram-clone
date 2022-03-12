package kr.or.spring.instagram_clone.dao;

public class UserDaoSqls {
	public static final String SELECT_ALL_BY_EMAIL = "SELECT id, name, password, email, create_date, modify_date FROM user WHERE email = :email";
	public static final String INSERT_USER =
			"INSERT INTO user(name, password, email, create_date, modify_date) "
			+ "VALUES (:name, :password, :email, :createDate, :modifyDate);";
}