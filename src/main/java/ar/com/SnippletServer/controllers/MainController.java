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

import ar.com.SnippletServer.data.layer.CategorySerivce;
import ar.com.SnippletServer.domain.Categoria;
import ar.com.SnippletServer.domain.SaveAjaxSnipplet;
import ar.com.SnippletServer.domain.Snipplet;
import ar.com.SnippletServer.dto.ButtonDTO;
import ar.com.SnippletServer.dto.CategoriaDTO;
import ar.com.SnippletServer.dto.HtmlSnippletDTO;
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
	
	@Autowired
	private CategorySerivce categoryService;
	
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
			throws JsonParseException, JsonMappingException {

		SendDTO sendDTO = new SendDTO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		sendDTO.setUsername(name);
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setNombre(nombreCategoria);
		sendDTO.setCategoriaDTO(categoriaDTO);
		CategoriaDTO categoriaDTO1=null;
		ModelAndView mav = new ModelAndView("frameSnipplet");
		try {
			categoriaDTO1 = persistencia.loadSavedFileForWeb(sendDTO);
		} catch (IOException e) {
			
			mav = new ModelAndView("components/button");
			ButtonDTO buttonDTO = new ButtonDTO();
			buttonDTO.setOnClick("getNewSniipletModal('"+nombreCategoria+"')");
			buttonDTO.setType("button");
			buttonDTO.setValue("agregar snipplet");
			mav.addObject("buttonDTO",buttonDTO);
			return mav;
		}
		mav.addObject("snipplets",categoriaDTO1.getSnipplets());
		mav.addObject("categoriaId",categoriaDTO1.getNombre());
		
		return mav;
	}
	
	
	@RequestMapping(value = "getNewSnippletModal")
	public ModelAndView getNewSnippletModal(@RequestBody String nombreCategoria){
		ModelAndView mav = new ModelAndView("modal/modalAgregarSnipplet");
		mav.addObject("nombreCategoria",nombreCategoria);
		return mav;
	}
	
	@RequestMapping(value ="htmlSnipplet")
	public ModelAndView htmlSnipplet(@RequestBody String htmlSnipplet){
		
		ModelAndView mav = new ModelAndView("HTML/newSnipplet");
		HtmlSnippletDTO htmlSnippletDTO = gsonUtility.getGson().fromJson(htmlSnipplet, HtmlSnippletDTO.class);
		mav.addObject("item",htmlSnippletDTO);
		return mav;
		
	}
	
	
	
	@RequestMapping(value = "saveAjaxSnipplet")
	public ModelAndView  saveAjaxSnipplet(@RequestBody String saveAjaxSnipplet){
		SaveAjaxSnipplet fromJson = gsonUtility.getGson().fromJson(saveAjaxSnipplet, SaveAjaxSnipplet.class);
		System.out.println(saveAjaxSnipplet);
		
		Categoria categoria = new Categoria();
		Snipplet snipplet= new Snipplet();
		
		categoria.setNombreCategoria(fromJson.getNombreCategoria());
		snipplet.setContenido(fromJson.getContenidoSnipplet());
		snipplet.setTitulo(fromJson.getTituloSnipplet());
		
		categoria.add(snipplet);
		
		
		return null;
		
	}
	
	
}
