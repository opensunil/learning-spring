/**
 * 
 */
package org.sunilbrown.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunil
 *
 */
@Controller
@RequestMapping("/welcome")
public class HelloController {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model){
		logger.info("Welcome home!");
		//model.addAttribute("message", "Hello, World!");
		return "home";
	}
	
	/*
	public ModelAndView helloWorld(){
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world");
		
		return model;
	}
	*/

}
