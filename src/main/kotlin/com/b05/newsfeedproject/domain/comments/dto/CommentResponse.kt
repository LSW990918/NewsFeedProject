package com.b05.newsfeedproject.domain.comments.dto

import kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader.Companion

data class CommentResponse(
    val id: Long?,
    val content: String,
    val date: String,

    )


