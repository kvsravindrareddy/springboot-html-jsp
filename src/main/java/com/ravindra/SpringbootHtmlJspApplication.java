package com.ravindra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@Controller
public class SpringbootHtmlJspApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(SpringbootHtmlJspApplication.class, args);
	}
	
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
}