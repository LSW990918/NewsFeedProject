package com.b05.newsfeedproject.domain.comments.service

import com.b05.newsfeedproject.domain.comments.dto.CommentResponse
import com.b05.newsfeedproject.domain.comments.dto.CreateCommentRequest
import com.b05.newsfeedproject.domain.comments.dto.UpdateCommentRequest

class CommentServiceImpl : CommentService {

    override fun createComment(commentRequest: CreateCommentRequest): CommentResponse {
        TODO("Not yet implemented")
    }
    override fun getCommentList(): List<CommentResponse> {
        TODO("Not yet implemented")
    }
    override fun updateComment(id: Int, commentRequest: UpdateCommentRequest): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun deleteComment(id: Int) {
        TODO("Not yet implemented")
    }
}