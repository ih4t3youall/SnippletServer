package ar.com.SnippletServer.data.layer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.SnippletServer.domain.Categoria;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Categoria, Integer>{ 

	
}
