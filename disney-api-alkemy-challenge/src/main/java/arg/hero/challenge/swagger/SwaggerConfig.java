package arg.hero.challenge.swagger;

import java.util.Arrays;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					.securityContexts(Arrays.asList(securityContext()))
					.securitySchemes(Arrays.asList(apiKey()))
					.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any())
				    .build();
		
	}
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder()
					.securityReferences(defaultAuth())
					.build();
	}
	
	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = (AuthorizationScope) new AuthorizationScopeBuilder().scope("global").description("accessEverything").build(); 
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Alkemy Challenge - Disney API",
				"Description: Creation of a simple Disney API using Spring-boot and Spring-security",
				"1.0",
				"",
				new Contact("Hernán Olmos", "https://github.com/her-o", "hernan.olmos11@gmail.com"),
				"",
				"",
				Collections.emptyList());
	}

}
