package com.b05.newsfeedproject.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.sql.Date
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.crypto.spec.SecretKeySpec


@PropertySource("classpath:jwt.yml")
@Slf4j
@Service
class JwtTokenProvider(
        @Value("\${secret-key}")
        private val secretKey: String,
        @Value("\${expiration-hours}")
        private val expirationHours:Long,
        @Value("\${issuer}")
        private val issuer:String
){

    fun createToken(userInfo:String) = Jwts.builder()
            .signWith(SecretKeySpec(secretKey.toByteArray(),SignatureAlgorithm.HS512.jcaName))
            .setSubject(userInfo)
            .setIssuer(issuer)
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .setExpiration(Date.from(Instant.now().plus(expirationHours,ChronoUnit.HOURS)))
            .compact()


    fun validateTokenAndGetSubject(token:String) = Jwts.parserBuilder()
            .setSigningKey(secretKey.toByteArray())
            .build()
            .parseClaimsJws(token)
            .body
            .subject
//
//
//    fun getAuthentication(token: String):Authentication{
//
//    }

    fun validateToken(token: String?): Boolean {
        try {
            val claims: Jws<Claims> = Jwts.parserBuilder().setSigningKey(secretKey.toByteArray())
                    .build()
                    .parseClaimsJws(token)
            return !claims.body.expiration.before(java.util.Date())
        } catch (e: Exception) {

            return false
        }
    }

    fun resolveToken(request:HttpServletRequest):String {
        return request.getHeader("Authorization")
    }


    fun getExpiration() = expirationHours
}