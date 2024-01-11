package com.b05.newsfeedproject.domain.postslike.repository

import com.b05.newsfeedproject.domain.postslike.model.PostLike
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

interface PostLikeRepository : JpaRepository<PostLike, Int> {

    fun findByUserIdAndPostId(userId: Int, postId: Int): PostLike?

    fun findByPostId(postId: Int): List<PostLike>

    @Transactional
    fun deleteByUserIdAndPostId(userId: Int, postId: Int)

}