package com.b05.newsfeedproject.domain.comments.service

import com.b05.newsfeedproject.domain.comments.dto.CommentResponse
import com.b05.newsfeedproject.domain.comments.dto.CreateCommentRequest
import com.b05.newsfeedproject.domain.comments.dto.UpdateCommentRequest

interface CommentService {

    fun createComment(userId: Int, postId: Int, request: CreateCommentRequest): CommentResponse
    fun getCommentList(postId: Int): List<CommentResponse>
    fun updateComment(userId: Int, postId: Int, commentId: Int, request: UpdateCommentRequest): CommentResponse
    fun deleteComment(userId: Int, postId: Int, commentId: Int)

}