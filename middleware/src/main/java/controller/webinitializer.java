package controller;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import collaboration.backend.Dbconfig;

public class webinitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{Dbconfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{webconfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		//any requests, it will be forwarded to DispatcherServlet
		return new String[]{"/"};
	}

}
