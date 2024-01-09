package com.b05.newsfeedproject.domain.posts.service

import com.b05.newsfeedproject.domain.posts.dto.CreatePostRequest
import com.b05.newsfeedproject.domain.posts.dto.PostResponse
import com.b05.newsfeedproject.domain.posts.dto.UpdatePostRequest
import org.springframework.http.ResponseEntity

interface PostService {

    fun createPost(request: CreatePostRequest): PostResponse

    fun getPostList(): List<PostResponse>

    fun getPost(postId: Int): PostResponse

    fun updatePost(postId: Int, request: UpdatePostRequest): PostResponse

    fun deletePost(postId: Int)
}