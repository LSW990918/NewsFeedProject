package com.b05.newsfeedproject.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
@EnableRedisRepositories
class RedisConfig(
        @Value("\${spring.data.redis.host}")
        private val host:String,
        @Value("\${spring.data.redis.port}")
        private val port:Int
) {


    @Bean
    fun redisConnectionFactory() = LettuceConnectionFactory(host,port)


    @Bean
    fun redisTemplate():RedisTemplate<String,Any> {
        val redisTemplate = RedisTemplate<String,Any>()
        redisTemplate.apply {
            keySerializer = StringRedisSerializer()
            valueSerializer = StringRedisSerializer()
            connectionFactory = redisConnectionFactory()

        }

        return redisTemplate
    }


}