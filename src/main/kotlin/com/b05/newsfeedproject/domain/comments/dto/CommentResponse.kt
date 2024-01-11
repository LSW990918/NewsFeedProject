package com.b05.newsfeedproject.domain.comments.dto

import com.b05.newsfeedproject.domain.user.model.User

//import kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader.Companion

data class CommentResponse(
    val id: Int,
    val content: String,
    val date: String,
    val user: User,
)


