package com.b05.newsfeedproject.domain.user.model.admin

import com.b05.newsfeedproject.domain.user.model.User
import com.b05.newsfeedproject.domain.user.model.UserType
import com.b05.newsfeedproject.domain.user.repository.UserRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDateTime



@Component
class AdminInitializer(
        private val userRepository: UserRepository,
        private val encoder: PasswordEncoder
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        userRepository.save(User(
                "admin",
                "관리자",
                encoder.encode("admin"),
                UserType.ADMIN,
                LocalDateTime.now(),
                null
        ))
    }
}