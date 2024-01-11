package com.b05.newsfeedproject.infra.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info

//@Configuration
class SwaggerConfig {

    //@Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .components(Components())
        .info(
            Info()
                .title("NewsFeed API")
                .description("NewsFeed API schema")
                .version("1.0.0")
        )
}