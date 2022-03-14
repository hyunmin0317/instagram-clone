package com.clone.instagram.service;

import lombok.RequiredArgsConstructor;
import com.clone.instagram.domain.comment.Comment;
import com.clone.instagram.domain.comment.CommentRepository;
import com.clone.instagram.domain.post.Post;
import com.clone.instagram.domain.post.PostRepository;
import com.clone.instagram.domain.user.User;
import com.clone.instagram.domain.user.UserRepository;
import com.clone.instagram.handler.ex.CustomApiException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Comment addComment (String text, long postId, long sessionId) {
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(sessionId).orElseThrow(()->{
            return new CustomApiException("유저 아이디를 찾을 수 없습니다.");
        });
        Comment comment = Comment.builder()
                .text(text)
                .post(post)
                .user(user)
                .build();
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }
}
