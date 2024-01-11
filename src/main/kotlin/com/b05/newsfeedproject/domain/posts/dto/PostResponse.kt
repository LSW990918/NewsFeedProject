package com.b05.newsfeedproject.domain.posts.dto

import com.b05.newsfeedproject.domain.user.model.User

data class PostResponse(
    val id: Int,
    val title: String,
    val content: String,
    val date: String,
    val user: User,
)