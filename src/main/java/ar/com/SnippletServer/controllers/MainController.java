package ar.com.SnippletServer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/inicio")
	public ModelAndView main() {

		ModelAndView mav = new ModelAndView("inicio");
		return mav;

	}
}
