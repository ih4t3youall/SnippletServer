package ar.com.SnippletServer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DynamicContentController {

	
	
	@RequestMapping(value ="getHtmlCategoria", method=RequestMethod.POST)
	public ModelAndView getHtmlCategoria(@RequestBody String categoria){
		
		
		ModelAndView mav = new ModelAndView("HTML/htmlCategoria");
		mav.addObject("item",categoria);
		
		return mav;
	}
	
	
}
