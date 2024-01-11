package com.b05.newsfeedproject.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.context.SecurityContext
import java.util.*

private const val SECURITY_SCHEME_NAME = "Authorization"
@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
            .components(Components()
            .addSecuritySchemes(SECURITY_SCHEME_NAME, SecurityScheme()
                    .name(SECURITY_SCHEME_NAME)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")))
    .addSecurityItem(SecurityRequirement().addList(SECURITY_SCHEME_NAME))
            .info(
                    Info()
                            .title("뉴스피드 API")
                            .description("뉴스피드 API schema")
                            .version("1.0.0")
            )


}