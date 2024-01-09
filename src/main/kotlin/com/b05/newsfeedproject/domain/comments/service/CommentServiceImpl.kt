package com.b05.newsfeedproject.domain.comments.service

import com.b05.newsfeedproject.domain.comments.dto.CommentResponse
import com.b05.newsfeedproject.domain.comments.dto.CreateCommentRequest
import com.b05.newsfeedproject.domain.comments.dto.UpdateCommentRequest
import com.b05.newsfeedproject.domain.comments.model.Comment
import com.b05.newsfeedproject.domain.comments.model.toResponse
import com.b05.newsfeedproject.domain.comments.repsitory.CommentRepository
import com.b05.newsfeedproject.domain.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull

class CommentServiceImpl(
    private val commentRepository: CommentRepository

) : CommentService {

    override fun createComment(request: CreateCommentRequest): CommentResponse {
        return commentRepository.save(
            Comment(
                content = request.content
            )
        ).toResponse()
    }

    override fun getCommentList(): List<CommentResponse> {
        return commentRepository.findAll().map { it.toResponse() }
    }

    override fun updateComment(postId: Int, commentId: Int, request: UpdateCommentRequest): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("comment", commentId)
        comment.content = request.content

        return commentRepository.save(comment).toResponse()
    }

    override fun deleteComment(postId: Int, commentId: Int) {

    }
}