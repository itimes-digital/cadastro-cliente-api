package com.builders.cad.cliente.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.builders.cad.cliente.controller")) 
            .paths(PathSelectors.any())
            .build().apiInfo(apiInfo())
			.securityContexts(Arrays.asList(actuatorSecurityContext()))
			.securitySchemes(Arrays.asList(basicAuthScheme()));
    }
	
	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl("").build();
	}


    private ApiInfo apiInfo() {

        return new ApiInfo("REST API with Spring Boot 2.1.4", 
				"API para cadastro de cliente e gerenciamento de dados para Builders", 
				"v1", "Terms Of Service URL", 
				new Contact("Mr. Anderson", "https://www.platformbuilders.io/", "saberemosjuntos@gmail.com"), 
				"GPL-3.0 License", "GPL-3.0 License", Collections.emptyList());
    }
    
	private SecurityContext actuatorSecurityContext() {
		return SecurityContext.builder().securityReferences(Arrays.asList(basicAuthReference()))
				.forPaths(PathSelectors.any()).build();
	}

	private SecurityScheme basicAuthScheme() {
		return new BasicAuth("basicAuth");
	}

	private SecurityReference basicAuthReference() {
		return new SecurityReference("basicAuth", new AuthorizationScope[0]);
	}


}
