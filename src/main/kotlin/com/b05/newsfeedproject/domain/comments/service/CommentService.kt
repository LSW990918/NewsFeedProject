package com.b05.newsfeedproject.domain.comments.service

import com.b05.newsfeedproject.domain.comments.dto.CommentResponse
import com.b05.newsfeedproject.domain.comments.dto.CreateCommentRequest
import com.b05.newsfeedproject.domain.comments.dto.UpdateCommentRequest

interface CommentService {

    fun createComment(commentRequest: CreateCommentRequest): CommentResponse
    fun getCommentList(): List<CommentResponse>
    fun updateComment(id: Int, commentRequest: UpdateCommentRequest): CommentResponse
    fun deleteComment(id: Int)
}