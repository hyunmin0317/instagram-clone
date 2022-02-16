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

import kr.or.spring.instagram_clone.dto.Likes;
import kr.or.spring.instagram_clone.dto.Post;

import static kr.or.spring.instagram_clone.dao.PostDaoSqls.*;

@Repository
public class PostDao {
	 private NamedParameterJdbcTemplate jdbc;
	    private SimpleJdbcInsert insertAction;
	    private RowMapper<Post> rowMapper = BeanPropertyRowMapper.newInstance(Post.class);

	    public PostDao(DataSource dataSource) {
	        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	        this.insertAction = new SimpleJdbcInsert(dataSource)
	                .withTableName("Post")
	                .usingGeneratedKeyColumns("postId");
	    }
	    
	    public List<Post> selectAll(Integer start, Integer limit) {
	    		Map<String, Integer> params = new HashMap<>();
	    		params.put("start", start);
	    		params.put("limit", limit);
	        return jdbc.query(SELECT_PAGING, params, rowMapper);
	    }
	    
	    public List<Post> selectName(Integer start, Integer limit, String name) {
    		Map<String, Object> params = new HashMap<>();
    		params.put("name", name);
    		params.put("start", start);
    		params.put("limit", limit);
        return jdbc.query(SELECT_PAGING_NAME, params, rowMapper);
    }
	    

		public Long insert(Post guestbook) {
			SqlParameterSource params = new BeanPropertySqlParameterSource(guestbook);
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
			jdbc.update("INSERT INTO likes(post_id, user_id) "
					+ "VALUES (:post_id, :user_id);", params);
		}
}