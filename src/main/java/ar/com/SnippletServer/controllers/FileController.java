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

import ar.com.SnippletServer.dao.UserDAO;
import ar.com.SnippletServer.domain.User;
import ar.com.SnippletServer.dto.SendDTO;
import ar.com.SnippletServer.persistencia.Persistencia;

@RestController
public class FileController {

	@Autowired
	private Persistencia persistencia;

	@Autowired
	private UserDAO userDAO;

	private ObjectMapper objMapper = new ObjectMapper();

	private boolean login(SendDTO sendDTO) {

		User user = userDAO.getUser(sendDTO.getUsername());

		if(user == null){
			
			userDAO.save(new User(sendDTO.getUsername(),sendDTO.getPassword()));
			user = new User(sendDTO.getUsername(),sendDTO.getPassword());
			
		}
		if (user.getName().equals(sendDTO.getUsername()) && user.getPassword().equals(sendDTO.getPassword())) {

			return true;

		} else {

			return false;

		}

	}

	@RequestMapping(value = "/guardarCategoria", method = RequestMethod.POST)
	public String guardarCategoria(@RequestBody String username)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objMapper = new ObjectMapper();
		SendDTO sendDTO = objMapper.readValue(username, SendDTO.class);
		boolean logeado = login(sendDTO);

		if (logeado) {

			String response = persistencia.save(sendDTO);
			return response;
		} else {
			String[] respuesta = new String[1];
			respuesta[0] = "usuario contraseña incorrecto";
			return objMapper.writeValueAsString(respuesta);
		}
	}

	@RequestMapping(value = "/listarServer", method = RequestMethod.POST)
	public String listarServer(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {

		SendDTO sendDTO = objMapper.readValue(json, SendDTO.class);
		boolean logeado = login(sendDTO);
		if (logeado) {
			String[] listDirectory = persistencia.listDirectory(sendDTO.getUsername());
			String writeValueAsString = objMapper.writeValueAsString(listDirectory);
			return writeValueAsString;
		} else {

			String[] respuesta = new String[1];
			respuesta[0] = "usuario contraseña incorrecto";
			String writeValueAsString = objMapper.writeValueAsString(respuesta);
			return writeValueAsString;

		}
	}

	@RequestMapping(value = "/returnCategory", method = RequestMethod.POST)
	public String returnCategory(@RequestBody String json)
			throws JsonParseException, JsonMappingException, IOException {

		SendDTO sendDTO = objMapper.readValue(json, SendDTO.class);

		if (login(sendDTO)) {
			String loadSavedFile = persistencia.loadSavedFile(sendDTO);

			System.out.println("valor que devuelve: " + loadSavedFile);

			return loadSavedFile;
		} else {

			String[] respuesta = new String[1];
			respuesta[0] = "usuario contraseña incorrecto";
			String writeValueAsString = objMapper.writeValueAsString(respuesta);
			return writeValueAsString;

		}
	}

	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
	public void deleteCategory(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		SendDTO sendDTO = objMapper.readValue(json, SendDTO.class);

		if (login(sendDTO)) {

			persistencia.deleteCategory(sendDTO);

		} else {

			String[] respuesta = new String[1];
			respuesta[0] = "usuario contraseña incorrecto";
			String writeValueAsString = objMapper.writeValueAsString(respuesta);

		}

	}

}
