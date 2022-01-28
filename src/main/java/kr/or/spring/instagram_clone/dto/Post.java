package kr.or.spring.instagram_clone.dto;

import java.util.Date;

public class Post {
	private Long postId;
	private Long userId;
	private String title;
	private String content;
	private String image;
	private Date date;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", image=" + image + ", date=" + date + "]";
	}
}