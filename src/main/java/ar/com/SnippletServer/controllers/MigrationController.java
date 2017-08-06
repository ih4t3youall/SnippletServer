package ar.com.SnippletServer.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.SnippletServer.domain.Snipplet;
import ar.com.SnippletServer.dto.CategoriaDTO;
import ar.com.SnippletServer.dto.SendDTO;
import ar.com.SnippletServer.persistencia.Persistencia;
import ar.com.SnippletServer.utilities.GsonUtility;

@RestController
public class MigrationController {

	
	@Autowired
	private Persistencia persistencia;
	
	@Autowired
	private GsonUtility gsonUtility;
	
	private static List<CategoriaDTO> categoriasDTO;
	
	@RequestMapping(value ="/iniciarMigracion" , method = RequestMethod.GET)
	public int getAllUsers() throws IOException {
		
		String [] files = persistencia.listDirectory("martin");
		categoriasDTO = new ArrayList<CategoriaDTO>();
		
		
		for(String file : files) {
			SendDTO sendDTO = new SendDTO();
			sendDTO.setUsername("martin");	
			
			CategoriaDTO catDTO = new CategoriaDTO();
			catDTO.setNombre(file);
			sendDTO.setCategoriaDTO(catDTO);
			CategoriaDTO recoveredCat = persistencia.loadSavedFileForWeb(sendDTO);
			System.out.println(recoveredCat.getNombre());
			List<Snipplet> snipplets = recoveredCat.getSnipplets();
			catDTO.setSnipplets(snipplets);
			categoriasDTO.add(catDTO);
			
		}
		
		return categoriasDTO.size();
		
	}
	
	@RequestMapping(value = "/getNumber", method = RequestMethod.GET)
	public String getNumber(int number) {
		
		return gsonUtility.getGson().toJson(categoriasDTO.get(number));
		
	}
	
	
}
