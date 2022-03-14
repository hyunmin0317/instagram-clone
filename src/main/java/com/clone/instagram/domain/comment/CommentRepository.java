package com.clone.instagram.domain.comment;

import com.clone.instagram.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteCommentsByPost(Post post);
}
