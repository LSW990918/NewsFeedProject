package com.b05.newsfeedproject.domain.postslike.model

import com.b05.newsfeedproject.domain.posts.model.Post
import jakarta.persistence.*

@Entity
@Table(name = "posts_like")
class PostLike(

    @ManyToOne
    @JoinColumn(name = "post_id")
    val post: Post,

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    val user: User

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?= null
}
