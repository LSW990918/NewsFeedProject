package com.b05.newsfeedproject.domain.user.model

import com.b05.newsfeedproject.domain.user.dto.SignInUserRequest
import com.b05.newsfeedproject.domain.user.dto.SignupUserRequest
import com.b05.newsfeedproject.domain.user.dto.UpdateUserRequest
import com.b05.newsfeedproject.domain.user.dto.UserResponse
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import java.util.Date


@Entity
@Table(name = "app_user")
class User(
        @Column(name = "email", nullable = false)
        var email:String,
        @Column(name = "password", nullable = false)
        var password:String,
        @Column(name = "nickname", nullable = false)
        var nickName:String,

        @Enumerated(EnumType.STRING)
        @Column(name = "type", nullable = false)
        var type:UserType = UserType.USER,

        @CreationTimestamp
        @Column(name = "create_at", nullable = true)
        var create_at:LocalDateTime? = null,
        @UpdateTimestamp
        @Column(name = "update_at", nullable = true)
        var update_at:LocalDateTime? = null
){


    companion object {
        fun from(request:SignupUserRequest,encoder:PasswordEncoder) = User(
                email = request.email,
                password = encoder.encode(request.password),
                nickName = request.nickName,
                create_at = LocalDateTime.now(),
                update_at = null
        )

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int? = null




    //프로필 수정 시 패스워드 암호화
    fun update(request:UpdateUserRequest,encoder: PasswordEncoder) {
        email = request.email
        password = encoder.encode(request.password)
        nickName = request.nickName
    }
}

fun User.toResponse() = UserResponse(
        id,
        email,
        nickName,
        password,
        type.toString(),
        create_at.toString(),
        update_at.toString()
)