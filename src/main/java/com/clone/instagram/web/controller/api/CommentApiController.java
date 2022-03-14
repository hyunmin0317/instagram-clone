package com.clone.instagram.web.controller.api;

import lombok.RequiredArgsConstructor;
import com.clone.instagram.config.auth.PrincipalDetails;
import com.clone.instagram.service.CommentService;
import com.clone.instagram.web.dto.comment.CommentUploadDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentUploadDto commentUploadDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(commentService.addComment(commentUploadDto.getText(), commentUploadDto.getPostId(), principalDetails.getUser().getId()), HttpStatus.OK);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK);
    }
}
