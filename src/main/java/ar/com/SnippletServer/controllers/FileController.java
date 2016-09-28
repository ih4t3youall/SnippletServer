package ar.com.SnippletServer.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.impl.ior.WireObjectKeyTemplate;

import ar.com.SnippletServer.dto.CategoriaDTO;
import ar.com.SnippletServer.dto.SendDTO;
import ar.com.SnippletServer.persistencia.Persistencia;

@RestController
public class FileController {
	
	
	
	
	@Autowired
	private Persistencia persistencia;
	
	private ObjectMapper objMapper = new ObjectMapper();
	
	@RequestMapping("/")
	public String index(){
		return "hola";
		
	}
//	{"password":"default","username":"martin","categoriaDTO":{"nombre":"overwatch","tags":null,"snipplets":[{"titulo":"tags","contenido":" M3L#1358 -> milton"}]}}
	
	
	@RequestMapping(value = "/guardarCategoria", method = RequestMethod.POST)
	public String guardarCategoria(@RequestBody String username) throws IOException {

		ObjectMapper objMapper = new ObjectMapper();
		
		String response = persistencia.save(objMapper.readValue(username, SendDTO.class));
		return response;
	}

	
	@RequestMapping(value = "/listarServer", method = RequestMethod.POST)
	public String listarServer(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException{
		
		SendDTO sendDTO = objMapper.readValue(json, SendDTO.class);
		String[] listDirectory = persistencia.listDirectory(sendDTO.getUsername());
		String writeValueAsString = objMapper.writeValueAsString(listDirectory);
		return writeValueAsString;
	}
	
	@RequestMapping(value = "/returnCategory", method = RequestMethod.POST)
	public String returnCategory(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException{
		
		SendDTO sendDTO = objMapper.readValue(json, SendDTO.class);
		String loadSavedFile = persistencia.loadSavedFile(sendDTO);
		
		System.out.println("valor que devuelve: "+loadSavedFile);
		
		
		
		return loadSavedFile;
	}
	
	
	
	
	
}
