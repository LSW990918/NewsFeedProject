package com.b05.newsfeedproject.domain.user.service

import com.b05.newsfeedproject.domain.exception.ModelNotFoundException
import com.b05.newsfeedproject.domain.exception.UserNotFoundException
import com.b05.newsfeedproject.domain.user.dto.*
import com.b05.newsfeedproject.domain.user.model.User
import com.b05.newsfeedproject.domain.user.model.toResponse
import com.b05.newsfeedproject.domain.user.repository.UserRepository
import com.b05.newsfeedproject.security.JwtTokenProvider
import jakarta.servlet.http.HttpServletRequest
import jakarta.transaction.Transactional
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val tokenProvider: JwtTokenProvider,
    private val encoder: PasswordEncoder,
    private val redisTemplate: RedisTemplate<String, Any>
) : UserService {
    override fun getUserById(userId: Int): UserResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)

        return user.toResponse()
    }


    @Transactional
    override fun signup(request: SignupUserRequest): UserResponse {
        //회원 가입 시 예외 처리(이미 존재하는 User)
        userRepository.findByEmail(request.email)?.let {
            throw IllegalArgumentException("email already exist!. use another email..")
        }


        return userRepository.save(
            User.from(
                request, encoder
            )
        ).toResponse()
    }


    @Transactional
    override fun updateUser(userId: Int, request: UpdateUserRequest): UserResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        user.update(request, encoder)
        return userRepository.save(user).toResponse()
    }

    @Transactional
    override fun signIn(request: SignInUserRequest): SignInResponse {
        //password와 email에 대한 Exception 처라
        val user = userRepository.findByEmail(request.email)?.let {
            if (!encoder.matches(request.password, it.password))
                throw UserNotFoundException()

            it
        } ?: throw UserNotFoundException()


        val token = tokenProvider.createToken("${user.nickName}:${user.email}:${user.id}")


        redisTemplate.opsForValue().set("JWT_TOKEN:${user.nickName}", token, tokenProvider.getExpiration())
        return SignInResponse(user.nickName, user.email, token)
    }


    @Transactional
    override fun signout() {


        val user =
            SecurityContextHolder.getContext().authentication.principal as org.springframework.security.core.userdetails.User


        if (redisTemplate.opsForValue().get("JWT_TOKEN:${user.username}") != null)
            redisTemplate.delete("JWT_TOKEN:${user.username}")


    }


}