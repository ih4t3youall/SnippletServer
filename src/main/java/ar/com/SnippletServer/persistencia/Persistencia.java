package ar.com.SnippletServer.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.SnippletServer.dto.CategoriaDTO;
import ar.com.SnippletServer.dto.SendDTO;

@Component
public class Persistencia {

	private String userHome;

	public Persistencia() {
		userHome = System.getProperty("user.home") + "/" + "SnippletServer" + "/";

	}

	public void createCategory(String filename) throws IOException {

		File file = new File(userHome + filename);
		if (!fileExist(filename)) {
			file.createNewFile();
		}
	}

	public boolean fileExist(String filename) {
		System.out.println("absolute path: " + userHome + filename);
		return new File(userHome + filename).exists();

	}

	public String save(SendDTO sendDTO) throws IOException {

		String relativePath = sendDTO.getUsername() + "/" + sendDTO.getCategoriaDTO().getNombre();
		System.out.println("relative path: " + relativePath);
		if (!fileExist(sendDTO.getUsername())) {
			System.out.println("folder created: " + sendDTO.getUsername());
			createFolder(sendDTO.getUsername());
		}

		if (fileExist(relativePath)) {
			System.out.println("file exist?: " + relativePath);
			deleteFile(relativePath);
		}
		FileOutputStream fileOut;
		ObjectOutputStream obj_out = null;
		try {
			fileOut = new FileOutputStream(userHome + relativePath);
			obj_out = new ObjectOutputStream(fileOut);
			obj_out.writeObject(sendDTO.getCategoriaDTO());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			obj_out.close();

		}

		return "200ok";
	}

	public String loadSavedFile(SendDTO sendDTO) throws IOException {
		File file = new File(userHome + sendDTO.getUsername() + "/" + sendDTO.getCategoriaDTO().getNombre());
		FileInputStream fin = new FileInputStream(file.getAbsolutePath());
		ObjectInputStream ois = new ObjectInputStream(fin);
		try {
			return new ObjectMapper().writeValueAsString((CategoriaDTO) ois.readObject());
		} catch (ClassNotFoundException e) {
			System.out.println("TIRE EXCEPTION!!!");
			e.printStackTrace();
		} finally {
			ois.close();
		}

		return "";

	}
	
	
	public CategoriaDTO loadSavedFileForWeb(SendDTO sendDTO) throws IOException {
		File file = new File(userHome + sendDTO.getUsername() + "/" + sendDTO.getCategoriaDTO().getNombre());
		FileInputStream fin = new FileInputStream(file.getAbsolutePath());
		ObjectInputStream ois = new ObjectInputStream(fin);
		try {
			return (CategoriaDTO) ois.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("TIRE EXCEPTION!!!");
			e.printStackTrace();
		} finally {
			ois.close();
		}

		return null;

	}

	public String[] listDirectory(String username) {
		System.out.println(userHome + "/" + username);
		return new File(userHome + "/" + username).list();

	}

	public void deleteAndCreateFile(String filename) throws IOException {
		File file = new File(userHome + filename);
		file.delete();
		file.createNewFile();
	}

	/**
	 * 
	 * Delete a file using as base the userHome property
	 * 
	 * @param filename
	 *            the file path after the userHome
	 */
	public void deleteFile(String filename) {
		File file = new File(userHome + filename);
		file.delete();
	}

	public void createFolder(String path) {
		System.out.println("create folder: " + userHome + path);
		new File(userHome + path).mkdir();

	}

	public void deleteCategory(SendDTO sendDTO) {
		deleteFile(sendDTO.getUsername() + "/" + sendDTO.getCategoriaDTO().getNombre());

	}

}