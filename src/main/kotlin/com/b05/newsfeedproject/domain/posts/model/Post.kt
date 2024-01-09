package com.b05.newsfeedproject.domain.posts.model

import com.b05.newsfeedproject.domain.posts.dto.PostResponse
import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val current = LocalDateTime.now()
val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")

@Entity
@Table(name = "posts")
class Post(

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "date", nullable = false)
    var date: String = current.format(formatter),

    //var comments: MutableList<Comment> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
}

fun Post.toResponse(): PostResponse {
    return PostResponse(
        id = id!!,
        title = title,
        content = content,
        date = date
    )
}