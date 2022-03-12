package kr.or.spring.instagram_clone.service;

import java.util.List;

import kr.or.spring.instagram_clone.dto.Comment;
import kr.or.spring.instagram_clone.dto.Likes;
import kr.or.spring.instagram_clone.dto.Post;
import kr.or.spring.instagram_clone.dto.User;

public interface PostService {
	public static final Integer LIMIT = 5;
	public List<Post> getPosts(Integer start, Long id);
	public List<Post> getPosts(Integer start, String name, Long id);
	public int deletePost(Long id, String ip);
	public Post addPost(Post post, String ip, String image, User user);
	public Likes addLikes(Likes likes, User user, Long post_id);
	public void deleteLikes(Long post_id, Long user_id);
	public int getCount();
	public Comment addComment(Comment comment, User user, Long post_id);	
}