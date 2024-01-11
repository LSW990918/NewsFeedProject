package com.b05.newsfeedproject.domain.exception

data class AccessDeniedException(val modelName: String) : RuntimeException(
    "You do not have access permission for $modelName"
)