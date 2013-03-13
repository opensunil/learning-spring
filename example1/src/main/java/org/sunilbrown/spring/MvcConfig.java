/**
 * 
 */
package org.sunilbrown.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author sunil
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.sunilbrown.spring")
public class MvcConfig {
	@Bean
	 public InternalResourceViewResolver configureInternalResourceViewResolver() {
	  InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	  resolver.setPrefix("/WEB-INF/views/");
	  resolver.setSuffix(".jsp");
	  return resolver;
	 }
}
