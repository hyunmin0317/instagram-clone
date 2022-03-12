package kr.or.spring.instagram_clone.dto;

public class UserRole {
	private int id;
	private int userId;
	private String roleName;

	public UserRole() {
	}

	public UserRole(int userId, String roleName) {
		this.userId = userId;
		this.roleName= roleName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}