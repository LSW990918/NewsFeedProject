package com.b05.newsfeedproject.domain.comments.controller

import com.b05.newsfeedproject.domain.comments.dto.CommentResponse
import com.b05.newsfeedproject.domain.comments.dto.CreateCommentRequest
import com.b05.newsfeedproject.domain.comments.dto.UpdateCommentRequest
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
class CommentController() {

    @PostMapping()
    fun createComment(@RequestBody commentRequest: CreateCommentRequest): ResponseEntity<CommentResponse> {
        TODO()
    }

    @GetMapping()
    fun getCommentList(): ResponseEntity<List<CommentResponse>> {
        TODO()
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Int,
        @RequestBody commentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse>{
        TODO()
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable commentId: Int): ResponseEntity<Unit>{
        TODO()
    }

}
