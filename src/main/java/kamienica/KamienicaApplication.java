package kamienica;

import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
public class KamienicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KamienicaApplication.class, args);
	}

//	@Bean
//	public Docket newsApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(apiInfo())
//				.select()
//				.paths(regex("/api/*.*"))
//				.build();
//	}
//
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**");
//			}
//		};
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder()
//				.title("Kamienica API")
//				.description("Spring Boot App")
//				.version("1.0")
//				.build();
//	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.securitySchemes(Lists.newArrayList(apiKey()))
				.apiInfo(apiInfo());

	}

	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration(
				"test-app-client-id",
				"test-app-client-secret",
				"test-app-realm",
				"test-app",
				"",
				ApiKeyVehicle.HEADER,
				"Authorization",
				"," /*scope separator*/);
	}

	@Bean
	SecurityScheme apiKey() {
		return new ApiKey("token", "token", "header");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring REST Sample with Swagger")
				.description("Spring REST Sample with Swagger")
				.version("2.0")
				.build();
	}

}
