package com.luckvicky.blur.global.config;

import com.luckvicky.blur.domain.board.model.entity.Board;
import com.luckvicky.blur.domain.channel.model.entity.Channel;
import com.luckvicky.blur.domain.league.model.entity.League;
import com.luckvicky.blur.domain.member.model.entity.Member;
import com.luckvicky.blur.global.jwt.model.ContextMember;

import static com.luckvicky.blur.global.constant.StringFormat.JWT;
import static com.luckvicky.blur.global.constant.StringFormat.TOKEN_PREFIX;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

@Configuration
public class SwaggerConfig {

    static {
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(Pageable.class, ContextMember.class);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .components(new Components()
                        .addSecuritySchemes(
                                TOKEN_PREFIX,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme(TOKEN_PREFIX)
                                        .bearerFormat(JWT)))
                ;
    }

    private Info apiInfo() {
        return new Info()
                .title("블러 API")
                .description("블러 Open API")
                .version("v1");
    }

    @Bean
    public GroupedOpenApi memberApi() {

        return GroupedOpenApi.builder()
                .group(Member.class.getSimpleName())
                .pathsToMatch("/v1/members/**", "/v1/auth/**")
                .addOpenApiCustomizer(openApi
                                -> openApi.addSecurityItem(
                                new SecurityRequirement().addList(TOKEN_PREFIX)
                        )
                )
                .build();

    }

    @Bean
    public GroupedOpenApi leagueApi() {

        return GroupedOpenApi.builder()
                .group(League.class.getSimpleName())
                .pathsToMatch("/v1/leagues/**")
                .addOpenApiCustomizer(openApi
                                -> openApi.addSecurityItem(
                                new SecurityRequirement().addList(TOKEN_PREFIX)
                        )
                )
                .build();

    }

    @Bean
    public GroupedOpenApi channelApi() {

        return GroupedOpenApi.builder()
                .group(Channel.class.getSimpleName())
                .pathsToMatch("/v1/channels/**")
                .addOpenApiCustomizer(openApi
                                -> openApi.addSecurityItem(
                                new SecurityRequirement().addList(TOKEN_PREFIX)
                        )
                )
                .build();

    }

    @Bean
    public GroupedOpenApi boardApi() {

        return GroupedOpenApi.builder()
                .group(Board.class.getSimpleName())
                .pathsToMatch("/v1/boards/**", "/v1/comments/**")
                .addOpenApiCustomizer(openApi
                                -> openApi.addSecurityItem(
                                new SecurityRequirement().addList(TOKEN_PREFIX)
                        )
                )
                .build();

    }

}
