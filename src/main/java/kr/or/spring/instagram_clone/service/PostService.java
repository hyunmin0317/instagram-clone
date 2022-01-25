package kr.or.spring.instagram_clone.service;

import java.util.List;

import kr.or.spring.instagram_clone.dto.Post;

public interface PostService {
	public static final Integer LIMIT = 5;
	public List<Post> getGuestbooks(Integer start);
	public int deleteGuestbook(Long id, String ip);
	public Post addGuestbook(Post guestbook, String ip);
	public int getCount();
}