package ar.com.SnippletServer.domain;

public class SaveAjaxSnipplet {

	private String nombreCategoria;
	private String tituloSnipplet;
	private String contenidoSnipplet;

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public String getTituloSnipplet() {
		return tituloSnipplet;
	}

	public void setTituloSnipplet(String tituloSnipplet) {
		this.tituloSnipplet = tituloSnipplet;
	}

	public String getContenidoSnipplet() {
		return contenidoSnipplet;
	}

	public void setContenidoSnipplet(String contenidoSnipplet) {
		this.contenidoSnipplet = contenidoSnipplet;
	}

}
