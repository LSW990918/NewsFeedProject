package com.b05.newsfeedproject.domain.user.dto

data class UpdateUserRequest(
    var email: String,
    var nickName: String,
    var password: String,
)