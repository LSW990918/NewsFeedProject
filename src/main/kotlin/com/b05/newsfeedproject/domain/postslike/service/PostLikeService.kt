package com.b05.newsfeedproject.domain.postslike.service

import com.b05.newsfeedproject.domain.postslike.dto.PostLikeResponse

interface PostLikeService {
    fun createPostLike(userId: Int, postId: Int)
    fun deletePostLike(userId: Int, postId: Int)
    fun getPostLike(postId: Int): PostLikeResponse?
}