package com.b05.newsfeedproject.domain.comments.service

import com.b05.newsfeedproject.domain.comments.dto.CommentResponse
import com.b05.newsfeedproject.domain.comments.dto.CreateCommentRequest
import com.b05.newsfeedproject.domain.comments.dto.UpdateCommentRequest
import com.b05.newsfeedproject.domain.comments.model.Comment
import com.b05.newsfeedproject.domain.comments.repsitory.CommentRepository
import com.b05.newsfeedproject.domain.exception.ModelNotFoundException
import com.b05.newsfeedproject.domain.posts.repository.PostRepository
import com.b05.newsfeedproject.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.format.DateTimeFormatter

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,

    ) : CommentService {

    @Transactional
    override fun createComment(userId: Int, postId: Int, request: CreateCommentRequest): CommentResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("user", userId)
        return commentRepository.save(
            Comment(
                content = request.content,
                post = post,
                user = user,
            )
        ).toResponse()
    }

    override fun getCommentList(postId: Int): List<CommentResponse> {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        return post.comments.map { it.toResponse() }
    }

    override fun updateComment(
        userId: Int,
        postId: Int,
        commentId: Int,
        request: UpdateCommentRequest
    ): CommentResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("comment", commentId)
        if (comment.post.id != post.id) {
            throw IllegalStateException("Post ID does not match with the comment's post ID")
        }
        comment.content = request.content
        return commentRepository.save(comment).toResponse()
    }

    override fun deleteComment(userId: Int, postId: Int, commentId: Int) {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("comment", commentId)
        if (comment.post.id != post.id) {
            throw IllegalStateException("Post ID does not match with the comment's post ID")
        }
        commentRepository.delete(comment)
    }

}

fun Comment.toResponse(): CommentResponse {
    val date = updatedDate?.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        ?: createdDate!!.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
    return CommentResponse(
        id = id!!,
        content = content,
        date = date,
        user = user
    )
}