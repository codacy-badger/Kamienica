package kamienica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class KamienicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KamienicaApplication.class, args);
    }

//    @Bean
//    public Docket v1Api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("Api Version 1")
//                .apiInfo(apiInfo())
//                .select()
//                .paths(regex(AbstractController.V1_PATH + "*.*"))
//                .build();
//    }

    @Bean
    public Docket other() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Budget All Endpoints")
                .apiInfo(apiInfo())
                .select()
//				.paths(regex("*"))
                .build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Budget API")
                .description("Spring Boot App")
                .version("2.0")
                .build();
    }

}
