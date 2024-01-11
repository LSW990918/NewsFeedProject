package com.b05.newsfeedproject.domain.user.controller

import com.b05.newsfeedproject.domain.user.dto.*
import com.b05.newsfeedproject.domain.user.service.UserService
import com.b05.newsfeedproject.security.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
        private val userService: UserService,
        private val jwtTokenProvider: JwtTokenProvider) {

    //프로필 조회
    @GetMapping("/users/{userId}/profile")
    fun getUserById(@PathVariable userId: Int): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId))
    }

    //회원가입
    @PostMapping("/signup")
    fun signUp(@RequestBody signupUserRequest: SignupUserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(signupUserRequest))
    }

    //프로필 수정
    @PutMapping("/users/{userId}/profile")
    fun updateUser(@PathVariable userId: Int, @RequestBody updateUserRequest: UpdateUserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, updateUserRequest))
    }

    //로그인
    @PostMapping("/signin")
    fun signIn(@RequestBody signInUserRequest: SignInUserRequest): ResponseEntity<SignInResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signIn(signInUserRequest))
    }


    @PostMapping("/signout")
    fun signout():ResponseEntity<Unit> {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signout())

    }

}