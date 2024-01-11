package com.b05.newsfeedproject.domain.comments.controller

import com.b05.newsfeedproject.domain.comments.dto.CommentResponse
import com.b05.newsfeedproject.domain.comments.dto.CreateCommentRequest
import com.b05.newsfeedproject.domain.comments.dto.UpdateCommentRequest
import com.b05.newsfeedproject.domain.comments.service.CommentService
import com.b05.newsfeedproject.domain.user.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/posts/{postId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping()
    fun createComment(
        @AuthenticationPrincipal user: User,
        @PathVariable postId: Int,
        @RequestBody commentRequest: CreateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(user.password.toInt(),postId,commentRequest))
    }

    @GetMapping()
    fun getCommentList(@PathVariable postId: Int): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentList(postId))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable postId: Int,
        @PathVariable commentId: Int,
        @RequestBody commentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(postId, commentId, commentRequest))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable postId: Int,
        @PathVariable commentId: Int
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(commentService.deleteComment(postId, commentId))
    }

}
