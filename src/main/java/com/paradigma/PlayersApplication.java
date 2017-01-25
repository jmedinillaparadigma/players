package com.paradigma;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * This is the main class for the spring boot application 
 * @author jmedinilla
 */
@SpringBootApplication
@EnableSwagger2
public class PlayersApplication {

	@Autowired
	private TypeResolver typeResolver;
	
	@Value("${app.version}")
	private String appVersion;
	
	public static void main(String[] args) {
		SpringApplication.run(PlayersApplication.class, args);
	}
	
	
	/**
	 * Initialization of the SpringFox Swagger
	 * @return {@link Docket} object
	 */
	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.paradigma.web"))
				.paths(regex("/players/.*"))
				.build()
				.pathMapping("/")
				.genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(
						newRule(typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class,WildcardType.class)), 
								typeResolver.resolve(WildcardType.class)))
				.useDefaultResponseMessages(false)
				.globalResponseMessage(
						RequestMethod.GET,
						newArrayList(new ResponseMessageBuilder().code(500)
								.message("500 message")
								.responseModel(new ModelRef("Error")).build()))
				.enableUrlTemplating(false)
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {

		return new ApiInfo("Players API Documentation",
						   "Players API",
						   appVersion, 
						   "Terms of service", 
						   new Contact("", "", ""), 
						   "License of API",
						     "API license URL");
	}
}
