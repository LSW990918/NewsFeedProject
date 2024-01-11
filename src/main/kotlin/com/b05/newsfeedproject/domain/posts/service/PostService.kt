package com.b05.newsfeedproject.domain.posts.service

import com.b05.newsfeedproject.domain.posts.dto.CreatePostRequest
import com.b05.newsfeedproject.domain.posts.dto.PostResponse
import com.b05.newsfeedproject.domain.posts.dto.UpdatePostRequest

interface PostService {

    fun createPost(userId: Int, request: CreatePostRequest): PostResponse

    fun getPostList(): List<PostResponse>

    fun getPost(postId: Int): PostResponse

    fun updatePost(userId: Int, postId: Int, request: UpdatePostRequest): PostResponse

    fun deletePost(userId: Int, postId: Int)
}

