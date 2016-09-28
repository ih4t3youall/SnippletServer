package ar.com.SnippletServer.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;



public class Persistencia {


	private String prefix;

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void crearCategoria(String filename) throws IOException {

		File file = new File(prefix + filename);
		if (!existeArchivo(filename)) {
			file.createNewFile();
		}
	}


	public void saveNewConfiguration(FileConfiguration fileConfiguration)
			throws IOException {
		File file;
			file = new File(fileConfiguration.getConfigurationPrefix());
		file.delete();
		
		file.createNewFile();
		FileOutputStream os = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(fileConfiguration);
		oos.close();
		os.close();

	}


	
	



	public boolean existeArchivo(String filename) {

		return new File(prefix + filename).exists();

	}
	
	

	public void guardar(Object obj, String filename) throws IOException {

		if (existeArchivo(filename)) {
			FileOutputStream fileOut;
			ObjectOutputStream obj_out = null;
			try {
				fileOut = new FileOutputStream(prefix + filename);
				obj_out = new ObjectOutputStream(fileOut);
				obj_out.writeObject(obj);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				obj_out.close();

			}

		} else {

			JOptionPane.showMessageDialog(null, "Error el archivo no existe!.");

		}
	}


	public CategoriaDTO recuperarArchivoGuardado(File file) {
		if (file.exists()) {
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				return (CategoriaDTO) ois.readObject();

			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				try {
					if (ois != null)
						ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}



	public String[] listDirectory() {

		return new File(prefix).list();

	}

	public void eliminarYCrearArchivo(String filename) throws IOException {
		File file = new File(prefix + filename);
		file.delete();
		file.createNewFile();
	}

	public void deleteFile(String filename) {
		File file = new File(prefix + filename);
		file.delete();
	}

	public void createFolder(String newPrefix) {
		new File(newPrefix).mkdir();
		
	}

}