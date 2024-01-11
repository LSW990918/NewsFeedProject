package com.b05.newsfeedproject.domain.exception

class UserNotFoundException : RuntimeException(
    "email or password is wrong!"
)
