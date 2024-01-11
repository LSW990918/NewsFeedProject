package com.b05.newsfeedproject.domain.user.dto

data class UserResponse(
    var id: Int?,
    var email: String,
    var nickName: String,
    var password: String,
    var type: String,
    var create_at: String,
    var update_at: String
)
