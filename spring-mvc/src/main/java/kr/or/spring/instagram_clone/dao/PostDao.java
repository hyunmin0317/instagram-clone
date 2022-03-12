package kr.or.spring.instagram_clone.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.spring.instagram_clone.dto.Comment;
import kr.or.spring.instagram_clone.dto.Likes;
import kr.or.spring.instagram_clone.dto.Post;

import static kr.or.spring.instagram_clone.dao.PostDaoSqls.*;

@Repository
public class PostDao {
	 private NamedParameterJdbcTemplate jdbc;
	    private SimpleJdbcInsert insertAction;
	    private RowMapper<Post> rowMapper = BeanPropertyRowMapper.newInstance(Post.class);
	    private RowMapper<Comment> rowMapper2 = BeanPropertyRowMapper.newInstance(Comment.class);

	    
	    public PostDao(DataSource dataSource) {
	        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	        this.insertAction = new SimpleJdbcInsert(dataSource)
	                .withTableName("Post")
	                .usingGeneratedKeyColumns("postId");
	    }
	    
	    public List<Post> selectAll(Integer start, Integer limit, Long id) {
	    		Map<String, Integer> params = new HashMap<>();
	    		List<Post> posts = jdbc.query(SELECT_PAGING, params, rowMapper);
	    		
	    		   		
	    		for(Post post : posts) {
	    			Map<String, Object> param = new HashMap<>();
	    			param.put("post_id", post.getId());
	    			post.setLikes(jdbc.queryForObject(LIKES_COUNT, param, Integer.class));
	    			param.put("user_id", id);
	    			int like = jdbc.queryForObject(LIKE_CHECK, param, Integer.class);
	    			post.setComments(jdbc.query(SELECT_COMMENT, param, rowMapper2));
	    			
	    			if (like == 0)
	    				post.setLike(false);
	    			else
	    				post.setLike(true);
	    		}
	    		
	        return posts;
	    }
	    
	    public List<Post> selectName(Integer start, Integer limit, String name, Long id) {
	    	Map<String, String> params = new HashMap<>();
	    	params.put("name", name);
    		List<Post> posts = jdbc.query(SELECT_PAGING_NAME, params, rowMapper);
    		
    		   		
    		for(Post post : posts) {
    			Map<String, Object> param = new HashMap<>();
    			param.put("post_id", post.getId());
    			post.setLikes(jdbc.queryForObject(LIKES_COUNT, param, Integer.class));
    			param.put("user_id", id);
    			int like = jdbc.queryForObject(LIKE_CHECK, param, Integer.class);
    			post.setComments(jdbc.query(SELECT_COMMENT, param, rowMapper2));
    			
    			if (like == 0)
    				post.setLike(false);
    			else
    				post.setLike(true);
    		}	
    		return posts;
	    }
	    

		public Long insert(Post post) {
			SqlParameterSource params = new BeanPropertySqlParameterSource(post);
			return insertAction.executeAndReturnKey(params).longValue();
		}
		
		public int deleteById(Long id) {
			Map<String, ?> params = Collections.singletonMap("id", id);
			return jdbc.update(DELETE_BY_ID, params);
		}
		
		public int selectCount() {
			Map<String, Integer> params = new HashMap<>();
			return jdbc.queryForObject("SELECT count(*) FROM Post", params, Integer.class);
		}
		
		public void addLikes(Likes likes) {
			Map<String, Object> params = new HashMap<>();
			params.put("post_id", likes.getPostId());
			params.put("user_id", likes.getUserId());

			// Insert Query를 위해서 update method를 사용했다.
			jdbc.update("INSERT INTO likes(post_id, user_id) "+ "VALUES (:post_id, :user_id);", params);
		}
		
		public void deleteLikes(Long post_id, Long user_id) {
			Map<String, Object> params = new HashMap<>();
			params.put("post_id", post_id);
			params.put("user_id", user_id);
			jdbc.update(DELETE_LIKE, params);
		}
		
		public void addComment(Comment comment) {
			Map<String, Object> params = new HashMap<>();
			params.put("user_id", comment.getUserId());
			params.put("post_id", comment.getPostId());
			params.put("user_name", comment.getUserName());
			params.put("content", comment.getContent());
			params.put("date", comment.getDate());
			
			// Insert Query를 위해서 update method를 사용했다.
			jdbc.update("INSERT INTO comment(user_id, post_id, user_name, content, date) "+ "VALUES (:user_id, :post_id, :user_name, :content, :date);", params);
		}
}