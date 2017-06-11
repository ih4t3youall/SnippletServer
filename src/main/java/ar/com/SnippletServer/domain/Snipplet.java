package ar.com.SnippletServer.domain;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Snipplet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String titulo;
	private String contenido;
	@ManyToOne
	private Categoria categoria;
	
	

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public boolean buscarTexto(String palabra) {

		if (contenido.indexOf(palabra) != -1 || titulo.indexOf(palabra) != -1) {

			return true;
		} else {

			return false;

		}

	}

}
