package ar.com.SnippletServer.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.SnippletServer.dto.CategoriaDTO;
import ar.com.SnippletServer.dto.SendDTO;
import ar.com.SnippletServer.persistencia.Persistencia;
import ar.com.SnippletServer.utilities.GsonUtility;

@Controller
public class MainController {


	@Autowired
	private Persistencia persistencia;

	private ObjectMapper objMapper = new ObjectMapper();

	@Autowired
	private GsonUtility gsonUtility;
	
	@RequestMapping("/inicio")
	public ModelAndView main() {

		ModelAndView mav = new ModelAndView("inicio");
		return mav;

	}

	@RequestMapping("/listar")
	public ModelAndView listar() throws IOException {
		ModelAndView mav = new ModelAndView("inicio");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		
		String[] listDirectory = persistencia.listDirectory(name);
		String writeValueAsString = objMapper.writeValueAsString(listDirectory);
		ObjectMapper map = new ObjectMapper();
		String[] array = map.readValue(writeValueAsString, String[].class);
		mav.addObject("lista", array);
		return mav;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	@RequestMapping(value ="/salvarCategoria" , method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void guardarCategoria(@RequestBody String sendable) throws JsonParseException, JsonMappingException, IOException{
		CategoriaDTO categoriaDTO = gsonUtility.getGson().fromJson(sendable, CategoriaDTO.class);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		SendDTO sendDTO = new SendDTO();
		sendDTO.setUsername(name);
		sendDTO.setCategoriaDTO(categoriaDTO);
		persistencia.save(sendDTO);
		
	}

	@RequestMapping(value = "/devolverCategoria", method = RequestMethod.POST)
	public ModelAndView returnCategory(@RequestBody String nombreCategoria)
			throws JsonParseException, JsonMappingException, IOException {

		SendDTO sendDTO = new SendDTO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		sendDTO.setUsername(name);
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setNombre(nombreCategoria);
		sendDTO.setCategoriaDTO(categoriaDTO);
		CategoriaDTO categoriaDTO1 = persistencia.loadSavedFileForWeb(sendDTO);
		ModelAndView mav = new ModelAndView("frameSnipplet");
		mav.addObject("snipplets",categoriaDTO1.getSnipplets());
		mav.addObject("categoriaId",categoriaDTO1.getNombre());
		
		return mav;
	}

}
