package com.b05.newsfeedproject.domain.posts.repository

import com.b05.newsfeedproject.domain.posts.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Int>{
}