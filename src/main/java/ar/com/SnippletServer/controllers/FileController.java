package ar.com.SnippletServer.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
	
	@RequestMapping("/")
	public String index(){
		return "hola";
		
	}
	
	
	
	@RequestMapping(value = "/listarArchivos", method = RequestMethod.POST)
	public String readBookmark(@RequestBody String categoria) {
		
		
	}

}
