package kr.or.spring.instagram_clone.dao;

public class PostDaoSqls {
	public static final String SELECT_PAGING = "SELECT * FROM Post ORDER BY id DESC";
	public static final String SELECT_PAGING_NAME = "SELECT * FROM Post WHERE user_name=:name ORDER BY id DESC";
	public static final String DELETE_BY_ID = "DELETE FROM Post WHERE id = :id";
	public static final String SELECT_COUNT = "SELECT count(*) FROM Post";
	public static final String LIKES_COUNT = "SELECT count(*) FROM Likes WHERE post_id = :post_id";
	public static final String LIKE_CHECK = "SELECT count(*) FROM Likes WHERE post_id = :post_id AND user_id = :user_id";
	public static final String DELETE_LIKE = "DELETE FROM Likes WHERE post_id = :post_id AND user_id = :user_id";
	public static final String SELECT_COMMENT = "SELECT * FROM Comment WHERE post_id = :post_id";
}