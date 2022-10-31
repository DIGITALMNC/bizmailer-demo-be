package com.sample.config.swagger;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2) //swagger 2.0 적용시
                .useDefaultResponseMessages(true) //Swagger에서 제공해주는 기본 응답코드(200, 401, 403, 404) 등의 노출여부
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sample")) //api 스펙이 작성되어 있는 패키지 (controller)
                .paths(PathSelectors.any()) // apis에 위치하는 API 중 특정 path를 선택
                .build()
                .directModelSubstitute(Pageable.class, MyPageable.class)
                .apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("BIZ MAILER")
                .description("비즈메일러 스웨거 테스트")
                .version("1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth()).build();
        return springfox
                .documentation
                .spi.service
                .contexts
                .SecurityContext
                .builder()
                .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    @Getter
    private static class MyPageable {
        @ApiParam(value = "페이지 번호(0..N)", example = "0")
        private Integer page;

        @ApiParam(value = "페이지 크기(기본 10)", example = "10", readOnly = true)
        private Integer size;

        @ApiParam(value = "정렬(컬럼명,ASC|DESC: 기본 등록일 최신순)", hidden = true)
        @Nullable
        private List<String> sort;
    }

}
