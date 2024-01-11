package com.b05.newsfeedproject.domain.comments.dto

//import kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader.Companion

data class CommentResponse(
    val id: Int,
    val content: String,
    val date: String,
)


