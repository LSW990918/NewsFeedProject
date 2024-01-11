package com.b05.newsfeedproject.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
        private val jwtTokenProvider: JwtTokenProvider,
        private val redisTemplate: RedisTemplate<String, Any>
) : OncePerRequestFilter() {


    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {


        val token = parseBearerToken(request)
        val user = parseUserSpecification(token)


        if (token != null && jwtTokenProvider.validateToken(token)) {
            val isLogout = redisTemplate.opsForValue().get("JWT_TOKEN:${user.username}")

            if (isLogout != null)
                UsernamePasswordAuthenticationToken.authenticated(user, token, user.authorities)
                        .apply {
                            details = WebAuthenticationDetails(request)
                        }.also {

                            SecurityContextHolder.getContext().authentication = it
                        }
        }

        filterChain.doFilter(request, response)

    }


    private fun parseBearerToken(request: HttpServletRequest): String? = request.getHeader(HttpHeaders.AUTHORIZATION)
            ?.takeIf { it.startsWith("Bearer ", true) }?.substring(7)


    private fun parseUserSpecification(token: String?) = (
            token?.takeIf { it.length >= 10 }?.let {
                jwtTokenProvider.validateTokenAndGetSubject(it)
            } ?: "anonymous:anonymous:anonymous").split(":").let {
                User(it[0], it[2], listOf(SimpleGrantedAuthority(it[1]))) 
    }


}

