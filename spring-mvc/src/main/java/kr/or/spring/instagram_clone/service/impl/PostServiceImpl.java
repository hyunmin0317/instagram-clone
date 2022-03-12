package kr.or.spring.instagram_clone.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import kr.or.spring.instagram_clone.dao.PostDao;
import kr.or.spring.instagram_clone.dto.Post;
import kr.or.spring.instagram_clone.dto.User;
import kr.or.spring.instagram_clone.service.PostService;
import kr.or.spring.instagram_clone.dto.Comment;
import kr.or.spring.instagram_clone.dto.Likes;


@Service
public class PostServiceImpl implements PostService{
	@Autowired
	PostDao postDao;

	@Override
	@Transactional
	public List<Post> getPosts(Integer start, Long id) {
		List<Post> list = postDao.selectAll(start, PostService.LIMIT, id);
		return list;
	}
	
	@Override
	@Transactional
	public List<Post> getPosts(Integer start, String name, Long id) {
		List<Post> list = postDao.selectName(start, PostService.LIMIT, name, id);
		return list;
	}

	@Override
	@Transactional(readOnly=false)
	public int deletePost(Long id, String ip) {
		int deleteCount = postDao.deleteById(id);
		return deleteCount;
	}

	@Override
	@Transactional(readOnly=false)
	public Post addPost(Post post, String ip, String image, User user) {
		post.setDate(new Date());
		post.setImage(image);
		post.setUserId(user.getId());
		post.setUserName(user.getName());
		post.setId(postDao.insert(post));
		return post;
	}

	@Override
	public int getCount() {
		return postDao.selectCount();
	}

	@Override
	public Likes addLikes(Likes likes, User user, Long post_id) {
		likes.setUserId(user.getId());
		likes.setPostId(post_id);
		postDao.addLikes(likes);
		return likes;
	}

	@Override
	public void deleteLikes(Long post_id, Long user_id) {
		postDao.deleteLikes(post_id, user_id);
	}
	
	@Override
	public Comment addComment(Comment comment, User user, Long post_id) {
		comment.setUserId(user.getId());
		comment.setPostId(post_id);
		comment.setUserName(user.getName());
		comment.setDate(new Date());
		postDao.addComment(comment);
		return comment;
	}
}