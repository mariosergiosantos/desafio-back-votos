package br.com.southsystem.cooperativismo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringFoxConfig implements WebMvcConfigurer {

    /*@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.southsystem.cooperativismo"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo custInfo() {

        return new ApiInfo("Test",
                "Spring Boot Services",
                "1.0",
                "TOS",
                new Contact("Test", "Test.com", "test@test.com"),
                "Test license",
                "License", Collections.emptyList());
    }*/

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
