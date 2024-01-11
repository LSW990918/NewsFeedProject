package com.b05.newsfeedproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class NewsFeedProjectApplication

fun main(args: Array<String>) {
    runApplication<NewsFeedProjectApplication>(*args)
}
