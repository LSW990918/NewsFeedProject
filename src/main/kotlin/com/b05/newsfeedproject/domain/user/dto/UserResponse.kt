package com.b05.newsfeedproject.domain.user.dto

import java.util.*

data class UserResponse(
        var id: Long?,
        var email: String,
        var nickName: String,
        var password:String,
        var type:String,
        var create_at: String,
        var update_at: String
)
