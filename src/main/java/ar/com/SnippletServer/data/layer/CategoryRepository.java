package ar.com.SnippletServer.data.layer;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.SnippletServer.domain.Categoria;

public interface CategoryRepository extends JpaRepository<Categoria, Integer>{ 

	
	Long saveCategory(Categoria categoria);
	
	
}
