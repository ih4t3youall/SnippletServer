package ar.com.SnippletServer.controllers;

import java.io.IOException;
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
	
	@RequestMapping(value ="/migracion" , method = RequestMethod.GET)
	public void getAllUsers() throws IOException {
		
		SendDTO sendDTO = new SendDTO();
		sendDTO.setUsername("martin");
		
		
		
		String [] files = persistencia.listDirectory(sendDTO.getUsername());
		
		
		for(String file : files) {
			
			
			CategoriaDTO catDTO = new CategoriaDTO();
			catDTO.setNombre(file);
			sendDTO.setCategoriaDTO(catDTO);
			CategoriaDTO recoveredCat = persistencia.loadSavedFileForWeb(sendDTO);
			System.out.println(recoveredCat.getNombre());
			List<Snipplet> snipplets = recoveredCat.getSnipplets();
			
			
			for (Snipplet snipplet : snipplets) {
				System.out.println(snipplet.getTitulo());
			}
			
			
		}
		
		persistencia.loadSavedFile(sendDTO);
		
		
		
	}
	
}
