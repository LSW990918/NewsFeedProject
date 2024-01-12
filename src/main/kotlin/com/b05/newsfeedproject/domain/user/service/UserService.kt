package com.b05.newsfeedproject.domain.user.service

import com.b05.newsfeedproject.domain.user.dto.*

interface UserService {
    fun getUserById(userId: Int): UserResponse
    fun signup(request: SignupUserRequest): UserResponse
    fun updateUser(userId: Int, request: UpdateUserRequest): UserResponse
    fun signIn(request: SignInUserRequest): SignInResponse
    fun signout()
}