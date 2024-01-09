package com.b05.newsfeedproject.domain.comments.repsitory

import com.b05.newsfeedproject.domain.comments.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Int> {
}