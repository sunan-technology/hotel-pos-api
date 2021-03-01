package com.sunan;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig extends WebMvcConfigurationSupport{
	
	 public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
	 
	@Bean
    public Docket productApi() {
		final String swaggerToken ="";
    	List<SecurityScheme> schemeList = new ArrayList<>();
    	schemeList.add(apiKey());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sunan"))
                .paths(PathSelectors.any())                
                .build()
//                .globalOperationParameters(Arrays.asList(new ParameterBuilder()
//                		.name("SessionID")
//            			.description("Active session id which is available in jwt token.")
//                        .modelRef(new ModelRef("string"))
//                        .parameterType("header")
//                        .required(true)
//                        .build(),
//                		new ParameterBuilder()
//                        .name("Authorization")
//                        .modelRef(new ModelRef("string"))
//                        .parameterType("header")
//                        .required(true)
//                        .hidden(true)
//                        .defaultValue("Bearer " + swaggerToken)
//                        .build()))
                .apiInfo(metaInfo()) 
                .securitySchemes(schemeList);
    }
	
	 
	
	private ApiKey apiKey() {
        return new ApiKey("authkey", "Authorization", "header");
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo =  new ApiInfoBuilder()
                .title("Schools REST API")
                .description("\"REST API for School ERP project\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Sunan Technology", "https://visionitsoftware.com/", "chakan@visionitsoftware.in"))
                .build();

        return apiInfo;
    }
    
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
   

    

}
