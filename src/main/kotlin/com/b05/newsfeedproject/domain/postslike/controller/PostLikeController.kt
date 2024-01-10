package com.b05.newsfeedproject.domain.postslike.controller

import com.b05.newsfeedproject.domain.postslike.dto.PostLikeResponse
import com.b05.newsfeedproject.domain.postslike.service.PostLikeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PostLikeController(
    private val postLikeService: PostLikeService
) {

    @GetMapping("/{postId}/like")
    fun getPostLike(@PathVariable postId: Int): ResponseEntity<PostLikeResponse> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(postLikeService.getPostLike(postId))
    }

    @PostMapping("/{postId}/like")
    fun createPostLike(@PathVariable postId: Int): ResponseEntity<Unit> {
        //        var userId = JWTHelper.~~
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(postLikeService.createPostLike(0, postId))
    }

    @DeleteMapping("/{postId}/like")
    fun deletePostLike(@PathVariable postId: Int): ResponseEntity<Unit> {
        //        var userId = JWTHelper.~~
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(postLikeService.deletePostLike(0, postId))
    }
}