package org.shop.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.Map;

import static springfox.documentation.builders.PathSelectors.regex;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private static final String SWAGGER_API_VERSION = "1,0";
    private static final String LICENSE_TEXT = "License";
    private static final String TITLE = "Product rest appi";
    private static final String DESCRIPTION = "RestFULL APPI for products";

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .license(LICENSE_TEXT)
                .version(SWAGGER_API_VERSION)
                .build();
    }



    Map<String, Object> container = new HashMap<>();
    {
        //create new bean: @Bean, @Service, @Controller, @Repository, @Component, @Entity
        container.put("Doket", new Docket(DocumentationType.SWAGGER_2));
        //@Autowired
        Object doket = container.get("Doket");
    }

    @Bean
    public Docket productsApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.shop"))
                .paths(regex("/api/shop.*"))
                .build()
                .apiInfo(apiInfo());
    }


}
