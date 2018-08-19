package org.iron.ultimate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "org.iron.ultimate")
public class SwaggerConfig {
	
	@Bean
	public Docket restfulApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("ultimate-iron")
				.select()
					.apis(apis())
					.paths(paths())
				.build()
				.apiInfo(apiInfo());
	}
	
	private Predicate<RequestHandler> apis() {
		return Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot"));
	}
	
	private Predicate<String> paths() {
		return PathSelectors.any();
	}
	
	private ApiInfo apiInfo() {
		
		return new ApiInfoBuilder()
				.title("Ultimate Iron")
				.description("Ultimate Iron Services")
				.termsOfServiceUrl("https://www.reddit.com/r/UltimateIron/")
				.contact(contact())
				.license("MIT License")
				.licenseUrl("https://opensource.org/licenses/MIT")
				.version("1.0")
				.build();
	}
	
	private Contact contact() {
		return new Contact("jj", "https://www.reddit.com/r/UltimateIron/", "forwardtojj@gmail.com");
	}
	
}
