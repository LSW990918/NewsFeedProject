package com.b05.newsfeedproject.domain.posts.controller

import com.b05.newsfeedproject.domain.posts.dto.CreatePostRequest
import com.b05.newsfeedproject.domain.posts.dto.PostResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/posts")
@RestController
class PostController() {

    @PostMapping
    fun createPost(@RequestBody createPostRequest: CreatePostRequest): ResponseEntity<PostResponse> {
        TODO()
    }

    @GetMapping()
    fun getPostList(): ResponseEntity<List<PostResponse>>  {
        TODO()
    }

    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Int): ResponseEntity<PostResponse>  {
        TODO()
    }

    @PutMapping("/{postId}")
    fun updatePost(
        @PathVariable postId: Int,
        @RequestBody createPostRequest: CreatePostRequest
    ): ResponseEntity<PostResponse>  {
        TODO()
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Int): ResponseEntity<PostResponse>  {
        TODO()
    }
}