package ar.com.SnippletServer.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.SnippletServer.persistencia.Persistencia;

@RestController
public class FileController {
	
	
	
	
	@Autowired
	private Persistencia persistencia;
	
	@RequestMapping("/")
	public String index(){
		return "hola";
		
	}
//	{"password":"default","username":"martin","categoriaDTO":{"nombre":"overwatch","tags":null,"snipplets":[{"titulo":"tags","contenido":" M3L#1358 -> milton"}]}}
	
	
	@RequestMapping(value = "/listarArchivos", method = RequestMethod.POST)
	public String readBookmark(@RequestBody String username) {
//		persistencia.save(obj, categoria, filename);

//		System.out.println("object: "+categoriaDTO.toString());
		System.out.println("username: "+username);
//		System.out.println("password: "+password);
		
		ObjectMapper objMapper = new ObjectMapper();
		JsonNode readTree =null;
		try {
			readTree = objMapper.readTree(username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String asText = readTree.get("username").asText();
		return asText;
	}

}
