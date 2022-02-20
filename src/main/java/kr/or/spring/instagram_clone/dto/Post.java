package kr.or.spring.instagram_clone.dto;

import java.util.Date;

public class Post {
	private Long id;
	private Long userId;
	private String userName;
	private String title;
	private String content;
	private String image;
	private Date date;
	private int likes;
	private boolean like;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	
	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public boolean getLike() {
		return like;
	}
	
	public void setLike(boolean like) {
		this.like = like;
	}

	@Override
	public String toString() {
		return "Post [Id=" + id + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", image=" + image + ", date=" + date + ", likes="+ likes+ ", like="+like+"]";
	}
}