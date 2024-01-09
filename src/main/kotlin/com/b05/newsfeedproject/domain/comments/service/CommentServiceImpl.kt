package com.b05.newsfeedproject.domain.comments.service

import com.b05.newsfeedproject.domain.comments.dto.CommentResponse
import com.b05.newsfeedproject.domain.comments.dto.CreateCommentRequest
import com.b05.newsfeedproject.domain.comments.dto.UpdateCommentRequest
import com.b05.newsfeedproject.domain.comments.model.Comment
import com.b05.newsfeedproject.domain.comments.model.toResponse
import com.b05.newsfeedproject.domain.comments.repsitory.CommentRepository
import com.b05.newsfeedproject.domain.exception.ModelNotFoundException
import com.b05.newsfeedproject.domain.posts.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull

class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository

) : CommentService {

    override fun createComment(postId: Int, request: CreateCommentRequest): CommentResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        return commentRepository.save(
            Comment(
                content = request.content,
                post = post,
            )
        ).toResponse()
    }

    override fun getCommentList(): List<CommentResponse> {
        return commentRepository.findAll().map { it.toResponse() }
    }

    override fun updateComment(postId: Int, commentId: Int, request: UpdateCommentRequest): CommentResponse {
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

    override fun deleteComment(postId: Int, commentId: Int) {
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