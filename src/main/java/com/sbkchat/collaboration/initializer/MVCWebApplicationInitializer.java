package com.sbkchat.collaboration.initializer;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.sbkchat.collaboration.config.EmailConfig;
import com.sbkchat.collaboration.config.HibernateConfig;
import com.sbkchat.collaboration.config.MvcConfig;

public class MVCWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[] {HibernateConfig.class, EmailConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[] {MvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"/"};
	}

}
