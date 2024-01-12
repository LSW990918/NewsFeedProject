package com.b05.newsfeedproject.domain.posts.service

import com.b05.newsfeedproject.domain.exception.AccessDeniedException
import com.b05.newsfeedproject.domain.exception.ModelNotFoundException
import com.b05.newsfeedproject.domain.posts.dto.CreatePostRequest
import com.b05.newsfeedproject.domain.posts.dto.PostResponse
import com.b05.newsfeedproject.domain.posts.dto.UpdatePostRequest
import com.b05.newsfeedproject.domain.posts.model.Post
import com.b05.newsfeedproject.domain.posts.repository.PostRepository
import com.b05.newsfeedproject.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : PostService {

    @Transactional
    override fun createPost(userId: Int, request: CreatePostRequest): PostResponse {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("user", userId)
        return postRepository.save(
            Post(
                title = request.title,
                content = request.content,
                user = user
            )
        ).toResponse()
    }

    override fun getPostList(): List<PostResponse> {
        val postList = postRepository.findAll().map { it.toResponse() }
        return postList
    }

    override fun getPost(postId: Int): PostResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        return post.toResponse()
    }

    @Transactional
    override fun updatePost(userId: Int, postId: Int, request: UpdatePostRequest): PostResponse {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("user", userId)
        //userId와 post.user 일치하는지 확인
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        if (user.id == post.user.id) {
            post.title = request.title
            post.content = request.content
            return post.toResponse()
        } else throw AccessDeniedException("post")
    }

    @Transactional
    override fun deletePost(userId: Int, postId: Int) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("user", userId)
        //userId와 post.user 일치하는지 확인
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        if (user.id == post.user.id) {
            postRepository.delete(post)
        } else throw AccessDeniedException("post")
    }
}

fun Post.toResponse(): PostResponse {
    val date = updatedDate?.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        ?: createdDate!!.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
    return PostResponse(
        id = id!!,
        title = title,
        content = content,
        date = date,
        user = user
    )
}

