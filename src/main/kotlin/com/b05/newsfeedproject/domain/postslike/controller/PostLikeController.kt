package com.b05.newsfeedproject.domain.postslike.controller

import com.b05.newsfeedproject.domain.postslike.dto.PostLikeResponse
import com.b05.newsfeedproject.domain.postslike.service.PostLikeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*

@RestController
class PostLikeController(
    private val postLikeService: PostLikeService
) {

    @GetMapping("/posts/{postId}/like")
    fun getPostLike(@PathVariable postId: Int): ResponseEntity<PostLikeResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postLikeService.getPostLike(postId))
    }

    // user.password : userId
    @PostMapping("/posts/{postId}/like")
    fun createPostLike(@AuthenticationPrincipal user: User, @PathVariable postId: Int): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(postLikeService.createPostLike(user.password.toInt(), postId))
    }

    // user.password : userId
    @DeleteMapping("/posts/{postId}/like")
    fun deletePostLike(@AuthenticationPrincipal user: User, @PathVariable postId: Int): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(postLikeService.deletePostLike(user.password.toInt(), postId))
    }
}