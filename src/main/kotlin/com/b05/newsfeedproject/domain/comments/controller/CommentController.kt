package com.b05.newsfeedproject.domain.comments.controller

import com.b05.newsfeedproject.domain.comments.dto.CommentResponse
import com.b05.newsfeedproject.domain.comments.dto.CreateCommentRequest
import com.b05.newsfeedproject.domain.comments.dto.UpdateCommentRequest
import com.b05.newsfeedproject.domain.comments.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/posts/{postId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping()
    fun createComment(
        @PathVariable postId: Int,
        @RequestBody commentRequest: CreateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(postId, commentRequest))
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
