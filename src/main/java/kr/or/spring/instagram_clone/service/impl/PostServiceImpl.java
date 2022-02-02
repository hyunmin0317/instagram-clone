package kr.or.spring.instagram_clone.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import kr.or.spring.instagram_clone.dao.PostDao;
import kr.or.spring.instagram_clone.dao.LogDao;
import kr.or.spring.instagram_clone.dto.Post;
import kr.or.spring.instagram_clone.dto.Log;
import kr.or.spring.instagram_clone.service.PostService;


@Service
public class PostServiceImpl implements PostService{
	@Autowired
	PostDao postDao;
	
	@Autowired
	LogDao logDao;

	@Override
	@Transactional
	public List<Post> getPosts(Integer start) {
		List<Post> list = postDao.selectAll(start, PostService.LIMIT);
		return list;
	}

	@Override
	@Transactional(readOnly=false)
	public int deletePost(Long id, String ip) {
		int deleteCount = postDao.deleteById(id);
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("delete");
		log.setRegdate(new Date());
		logDao.insert(log);
		return deleteCount;
	}

	@Override
	@Transactional(readOnly=false)
	public Post addPost(Post post, String ip, String image) {
		post.setDate(new Date());
		post.setImage(image);
		Long id = postDao.insert(post);
		post.setPostId(id);
		
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("insert");
		log.setRegdate(new Date());
		logDao.insert(log);
		
		return post;
	}

	@Override
	public int getCount() {
		return postDao.selectCount();
	}
	
	
}