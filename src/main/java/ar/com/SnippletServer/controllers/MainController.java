package ar.com.SnippletServer.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.SnippletServer.dto.CategoriaDTO;
import ar.com.SnippletServer.dto.SendDTO;
import ar.com.SnippletServer.persistencia.Persistencia;

@Controller
public class MainController {

	private String TEST_CONSTANT = "martin";

	@Autowired
	private Persistencia persistencia;

	private ObjectMapper objMapper = new ObjectMapper();

	@RequestMapping("/inicio")
	public ModelAndView main() {

		ModelAndView mav = new ModelAndView("inicio");
		return mav;

	}

	@RequestMapping("/listar")
	public ModelAndView listar() throws IOException {
		ModelAndView mav = new ModelAndView("inicio");
		String[] listDirectory = persistencia.listDirectory(TEST_CONSTANT);
		String writeValueAsString = objMapper.writeValueAsString(listDirectory);
		ObjectMapper map = new ObjectMapper();
		String[] array = map.readValue(writeValueAsString, String[].class);
		mav.addObject("lista", array);
		return mav;
	}

	@RequestMapping(value = "/devolverCategoria", method = RequestMethod.POST)
	public String returnCategory(@RequestBody String nombreCategoria)
			throws JsonParseException, JsonMappingException, IOException {

		SendDTO sendDTO = new SendDTO();
		sendDTO.setUsername(TEST_CONSTANT);
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setNombre(nombreCategoria);
		sendDTO.setCategoriaDTO(categoriaDTO);
		CategoriaDTO categoriaDTO1 = persistencia.loadSavedFileForWeb(sendDTO);categoriaDTO1.getSnipplets();
		return null;
	}

}
