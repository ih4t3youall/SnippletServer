package ar.com.SnippletServer.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {

	@Id
	private Long id;
	private String nombreCategoria;
	@OneToMany(mappedBy = "categoria")
	private List<Snipplet> snipplets;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public List<Snipplet> getSnipplets() {
		return snipplets;
	}

	public void setSnipplets(List<Snipplet> snipplets) {
		this.snipplets = snipplets;
	}

	
	public void add(Snipplet snipplet){
		
		if(snipplets == null)
			snipplets = new ArrayList<Snipplet>();
		
		snipplets.add(snipplet);
		
		
	}
}
