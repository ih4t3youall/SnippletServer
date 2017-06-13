package ar.com.SnippletServer.data.layer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.SnippletServer.domain.Categoria;
@Service
public class CategorySerivce {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	public void saveCategory(Categoria categoria) {
	
		categoryRepository.save(categoria);
		
	}

}
