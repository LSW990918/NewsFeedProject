package com.b05.newsfeedproject.domain.exception

data class AlreadyExistException(val modelName:String):RuntimeException(
            "$modelName Data already exists"
)

