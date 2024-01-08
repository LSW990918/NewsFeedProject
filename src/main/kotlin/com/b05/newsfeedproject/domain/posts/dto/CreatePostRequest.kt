package com.b05.newsfeedproject.domain.posts.dto

data class CreatePostRequest(
    val title: String,
    val content: String,
)