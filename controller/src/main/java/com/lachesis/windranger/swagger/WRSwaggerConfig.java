package com.lachesis.windranger.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 项目名称：
 *
 * @description:
 * @author
 * @create_time：
 * @version V1.0.0
 *
 */
@Configuration  
@EnableWebMvc  
@EnableSwagger2  
@ComponentScan(basePackages ={"com.lachesis.windranger.authentication.controller"})  
// Loads the spring beans required by the framework
public class WRSwaggerConfig
{

	/** 
	 * Every Docket bean is picked up by the swagger-mvc framework - allowing for multiple 
	 * swagger groups i.e. same code base multiple swagger resource listings. 
	 */  
	@Bean  
	public Docket customDocket(){  

		Docket aDocket = new Docket(DocumentationType.SWAGGER_2);
		
		Contact authorContact = new Contact("WindRanger", "","shiwei.hu@lachesis-mh.com"); 		
		ApiInfo apiInfo = new ApiInfo(
					"Lachesis WindRanger API Document", 
	                "WindRanger用于构建联新整体智慧医院的接口平台",
	                "V1.0",
	                "", 
	                authorContact,
	                "",
	                ""
				);			
		aDocket.apiInfo(apiInfo);
		
		return aDocket;  
	}  



	/*private SpringSwaggerConfig springSwaggerConfig;

	 *//**
	 * Required to autowire SpringSwaggerConfig
	 *//*
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig)
    {
        this.springSwaggerConfig = springSwaggerConfig;
    }

	  *//**
	  * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
	  * framework - allowing for multiple swagger groups i.e. same code base
	  * multiple swagger resource listings.
	  *//*
    @Bean
    public SwaggerSpringMvcPlugin customImplementation()
    {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(
        		".*?");
    }



    private ApiInfo apiInfo()
    {	
        ApiInfo apiInfo = new ApiInfo(
                "Lachesis WindRanger API Document", 
                "WindRanger用于构建医院的接口平台。<br/>已经集成的组件为Authentication, EMR <br/>支持在上运行的产品有MTR, COMS",
                "My Apps API terms of service", 
                "shiwei.hu@lachesis-mh.com", 
                "My Apps API Licence Type",
                "My Apps API License URL");



        return apiInfo;
    }*/
}