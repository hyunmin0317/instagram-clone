package kr.or.spring.instagram_clone.dto;

public class Likes {
	private Long id;
	private Long postId;
	private Long userId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Likes [id=" + id + ", postId=" + postId + ", userId=" + userId + "]";
	}
}
