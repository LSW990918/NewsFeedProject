package com.b05.newsfeedproject.domain.comments.service

import com.b05.newsfeedproject.domain.comments.dto.CommentResponse
import com.b05.newsfeedproject.domain.comments.dto.CreateCommentRequest
import com.b05.newsfeedproject.domain.comments.dto.UpdateCommentRequest
import com.b05.newsfeedproject.domain.posts.model.Post

interface CommentService {

    fun createComment(postId: Int, request: CreateCommentRequest): CommentResponse
    fun getCommentList(postId: Int): List<CommentResponse>
    fun updateComment(postId: Int, commentId: Int, request: UpdateCommentRequest): CommentResponse
    fun deleteComment(postId: Int, commentId: Int)

}