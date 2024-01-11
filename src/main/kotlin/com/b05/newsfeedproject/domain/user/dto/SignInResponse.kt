package com.b05.newsfeedproject.domain.user.dto

import io.swagger.v3.oas.annotations.media.Schema

data class SignInResponse(
    @Schema(description = "UserName", example = "홍길동")
    val nickName: String,
    @Schema(description = "UserEmail", example = "test@gmail.com")
    val email: String,
    val token: String
)
