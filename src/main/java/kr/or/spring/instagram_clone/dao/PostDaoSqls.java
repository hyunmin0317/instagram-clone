package kr.or.spring.instagram_clone.dao;

public class PostDaoSqls {
	public static final String SELECT_PAGING = "SELECT * FROM Post ORDER BY postId DESC limit :start, :limit";
	public static final String DELETE_BY_ID = "DELETE FROM Post WHERE postId = :id";
	public static final String SELECT_COUNT = "SELECT count(*) FROM Post";
}