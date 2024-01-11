package com.b05.newsfeedproject.domain.posts.controller

import com.b05.newsfeedproject.domain.posts.dto.CreatePostRequest
import com.b05.newsfeedproject.domain.posts.dto.PostResponse
import com.b05.newsfeedproject.domain.posts.dto.UpdatePostRequest
import com.b05.newsfeedproject.domain.posts.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*

@RequestMapping("/posts")
@RestController
class PostController(
    private val postService: PostService
) {

    @PostMapping
    fun createPost(
        @AuthenticationPrincipal user: User,
        @RequestBody createPostRequest: CreatePostRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(postService.createPost(user.password.toInt(), createPostRequest))
    }

    @GetMapping
    fun getPostList(): ResponseEntity<List<PostResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostList())
    }

    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Int): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPost(postId))
    }

    @PutMapping("/{postId}")
    fun updatePost(
        @AuthenticationPrincipal user: User,
        @PathVariable postId: Int,
        @RequestBody updatePostRequest: UpdatePostRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.updatePost(user.password.toInt(), postId, updatePostRequest))
    }

    @DeleteMapping("/{postId}")
    fun deletePost(
        @AuthenticationPrincipal user: User,
        @PathVariable postId: Int
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(postService.deletePost(user.password.toInt(), postId))
    }
}