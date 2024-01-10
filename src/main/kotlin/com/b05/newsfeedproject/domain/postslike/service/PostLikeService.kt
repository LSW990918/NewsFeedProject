package com.b05.newsfeedproject.domain.postslike.service

import com.b05.newsfeedproject.domain.posts.dto.PostResponse

interface PostLikeService {

    fun createPostLike(userId:Int, postId: Int)

    fun deletePostLike(userId:Int, postId: Int)
}