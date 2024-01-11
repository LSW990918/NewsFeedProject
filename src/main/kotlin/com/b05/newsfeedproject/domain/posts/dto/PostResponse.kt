package com.b05.newsfeedproject.domain.posts.dto

data class PostResponse(
    val id: Int,
    val title: String,
    val content: String,
    val date: String
)