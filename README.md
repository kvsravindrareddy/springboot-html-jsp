# springboot-html-jsp
Spring Boot Thymeleaf with JSP custom configuration for Thymeleaf

Step 1 : pom.xml

Add the below dependencies for Spring boot pom.xml file

    <dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		</dependency>
    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>

Step 2 : 
Add the below properties to conigure the JSP pages for Spring boot application.

spring.mvc.view.prefix:/WEB-INF/jsp/
spring.mvc.view.suffix:.jsp
spring.thymeleaf.view-names=thymeleaf/*

Step 3 : Create custom configuration class to load the html templates and html file should be starts with as per the below view configuration below.

package com.ravindra.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
 
@Configuration
public class ThymeleafViewResolverConfig {
 
    @Bean
    public ViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(thymeleafTemplateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(0);
        // Important!!
        // th_page1.html, th_page2.html, ...
        viewResolver.setViewNames(new String[] { "th_*" });
        return viewResolver;
    }
 
    // Thymeleaf template engine with Spring integration
    @Bean
    public SpringTemplateEngine thymeleafTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }
 
    @Bean
    public SpringResourceTemplateResolver springResourceTemplateResolver() {
        return new SpringResourceTemplateResolver();
    }
 
    // Thymeleaf template resolver serving HTML 5
    @Bean
    public ITemplateResolver thymeleafTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }
  
}

Step 4: Write the below Controllers

@RequestMapping(value = "/hellojsp", method = RequestMethod.GET)
    public ModelAndView showJsp() {
    	Employee emp = new Employee();
    	emp.setName("RavindraReddy");
    	ModelAndView modelView = new ModelAndView("hello");
    	modelView.addObject("name", emp.getName());
        return modelView;
    }
    
    @RequestMapping(value = "/hellohtml", method = RequestMethod.GET)
    public ModelAndView showHtml() {
    	Employee emp = new Employee();
    	emp.setName("RavindraReddy");
    	ModelAndView modelView = new ModelAndView("th_hello");
    	modelView.addObject("name", emp.getName());
        return modelView;
    }

