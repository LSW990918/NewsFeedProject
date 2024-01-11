package com.b05.newsfeedproject.domain.postslike.service

import com.b05.newsfeedproject.domain.exception.ModelNotFoundException
import com.b05.newsfeedproject.domain.posts.repository.PostRepository
import com.b05.newsfeedproject.domain.postslike.dto.PostLikeResponse
import com.b05.newsfeedproject.domain.postslike.model.PostLike
import com.b05.newsfeedproject.domain.postslike.repository.PostLikeRepository
import com.b05.newsfeedproject.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostLikeServiceImpl(
    private val postRepository: PostRepository,
    private val postLikeRepository: PostLikeRepository,
    private val userRepository: UserRepository,
) : PostLikeService {
    override fun createPostLike(userId: Int, postId: Int) {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("user", userId)
        // post.userId 추가
        return (0 != userId).let {
            if (it)
                postLikeRepository.findByUserIdAndPostId(userId, postId) ?: postLikeRepository.save(
                    PostLike(
                        post,
                        user
                    )
                )
        }
    }

    override fun deletePostLike(userId: Int, postId: Int) {
        postLikeRepository.deleteByUserIdAndPostId(userId, postId)
    }

    override fun getPostLike(postId: Int): PostLikeResponse? {
        return PostLikeResponse(postId, postLikeRepository.findByPostId(postId).size)
    }
}