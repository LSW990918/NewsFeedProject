package com.b05.newsfeedproject.domain.posts.service

import com.b05.newsfeedproject.domain.exception.ModelNotFoundException
import com.b05.newsfeedproject.domain.posts.dto.CreatePostRequest
import com.b05.newsfeedproject.domain.posts.dto.PostResponse
import com.b05.newsfeedproject.domain.posts.dto.UpdatePostRequest
import com.b05.newsfeedproject.domain.posts.model.Post
import com.b05.newsfeedproject.domain.posts.repository.PostRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter

@Service
class PostServiceImpl(
    private val postRepository: PostRepository
) : PostService {

    @Transactional
    override fun createPost(request: CreatePostRequest): PostResponse {
        return postRepository.save(
            Post(
                title = request.title,
                content = request.content
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
    override fun updatePost(postId: Int, request: UpdatePostRequest): PostResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        post.title = request.title
        post.content = request.content

        return post.toResponse()
    }

    @Transactional
    override fun deletePost(postId: Int) {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        postRepository.delete(post)
    }
}

fun Post.toResponse(): PostResponse {
    val date = updatedDate?.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        ?: createdDate!!.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
    return PostResponse(
        id = id!!,
        title = title,
        content = content,
        date = date
    )
}

