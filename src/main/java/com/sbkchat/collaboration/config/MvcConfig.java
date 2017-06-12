package com.sbkchat.collaboration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.sbkchat.collaboration")
public class MvcConfig extends WebMvcConfigurerAdapter{

	// configuration to load the static resources
	public void addResourcesHandler(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	// configuration for view Resolver
	@Bean
	public ViewResolver configViewResolver(){
		
		InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
		viewResolve.setPrefix("/WEB-INF/views");
		viewResolve.setSuffix(".jsp");
		return viewResolve;
	}
	
	// use DefaultServletHandlerConfigurer
	public void configDefaultServletHandling(DefaultServletHandlerConfigurer configure)
	{
		configure.enable();
	}
	
	
}
