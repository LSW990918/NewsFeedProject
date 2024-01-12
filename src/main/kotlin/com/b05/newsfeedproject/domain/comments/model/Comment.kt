package com.b05.newsfeedproject.domain.comments.model

import com.b05.newsfeedproject.domain.posts.model.Post
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener::class)
data class Comment(
    @Column(name = "content", nullable = false)
    var content: String,

    @CreatedDate
    @Column(name = "created_at")
    var createdDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedDate: LocalDateTime? = null,

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    //var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    var post: Post,


    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
}

