package com.b05.newsfeedproject.domain.postslike.service

import com.b05.newsfeedproject.domain.exception.ModelNotFoundException
import com.b05.newsfeedproject.domain.posts.repository.PostRepository
import com.b05.newsfeedproject.domain.postslike.model.PostLike
import com.b05.newsfeedproject.domain.postslike.repository.PostLikeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostLikeServiceImpl(
    private val postRepository: PostRepository,
    private val postLikeRepository: PostLikeRepository,
//    private val userRepository: UserRepository,
) : PostLikeService {
    override fun createPostLike(userId: Int, postId: Int) {
//        postId와  userId가 있고,
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
//        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("user", userId)
//        post의 게시자가 user가 아니며
        return (postId != userId).let {
            if(it)
                // 이미 좋아요가 안 눌렸다면
                postLikeRepository.findByUserIdAndPostId(userId,postId) ?:
                postLikeRepository.save(PostLike(post))
        }
    }

    override fun deletePostLike(userId: Int, postId: Int) {
        // postId와 userId로 postLike 삭제
        postLikeRepository.deleteByUserIdAndPostId(userId,postId)
    }
}