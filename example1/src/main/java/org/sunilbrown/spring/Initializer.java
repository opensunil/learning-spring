/**
 * 
 */
package org.sunilbrown.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author sunil
 *
 */
public class Initializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext arg0) throws ServletException {
		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		  mvcContext.register(MvcConfig.class);
		
		  ServletRegistration.Dynamic dispatcher = arg0.addServlet(
				    "dispatcher", new DispatcherServlet(mvcContext));
				  dispatcher.setLoadOnStartup(1);
				  dispatcher.addMapping("/app/*");
	}	
}
