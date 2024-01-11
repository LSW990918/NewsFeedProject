package com.b05.newsfeedproject.domain.user.repository

import com.b05.newsfeedproject.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {

    fun findByEmail(email: String): User?

}