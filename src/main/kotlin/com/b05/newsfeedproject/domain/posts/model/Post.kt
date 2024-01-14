package com.b05.newsfeedproject.domain.posts.model

import com.b05.newsfeedproject.domain.comments.model.Comment
import com.b05.newsfeedproject.domain.user.model.User
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "post")
@EntityListeners(AuditingEntityListener::class)
class Post(

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @CreatedDate
    @Column(name = "created_at")
    var createdDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedDate: LocalDateTime? = null,

    @OneToMany(
        mappedBy = "post",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY,
    )
    var comments: MutableList<Comment> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
}

