package com.b05.newsfeedproject.domain.exception

data class ModelNotFoundException(val modelName:String,val id:Int):RuntimeException(
            "${modelName} is not found in id:${id}"
)

