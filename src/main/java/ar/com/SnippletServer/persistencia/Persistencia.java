package ar.com.SnippletServer.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;


@Component
public class Persistencia {


	private String userHome;
	
	
	public Persistencia(){
		userHome = System.getProperty("user.home") + "/";
		
	}


	public void createCategory(String filename) throws IOException {

		File file = new File(userHome + filename);
		if (!fileExist(filename)) {
			file.createNewFile();
		}
	}


//	public void saveNewConfiguration(FileConfiguration fileConfiguration)
//			throws IOException {
//		File file;
//			file = new File(fileConfiguration.getConfigurationPrefix());
//		file.delete();
//		
//		file.createNewFile();
//		FileOutputStream os = new FileOutputStream(file);
//		ObjectOutputStream oos = new ObjectOutputStream(os);
//		oos.writeObject(fileConfiguration);
//		oos.close();
//		os.close();
//
//	}


	
	


	/**
	 * 
	 * check if a file exist using as base the userHome property
	 * 
	 * @param filename the file path after the userHome
	 */
	public boolean fileExist(String filename) {

		return new File(userHome + filename).exists();

	}
	
	

	public void save(Object obj,String folderName, String filename) throws IOException {

		String relativePath = folderName+"/"+ filename;
		
		if (fileExist(relativePath)) {
			deleteFile(folderName+"/"+ filename);
		}
			FileOutputStream fileOut;
			ObjectOutputStream obj_out = null;
			try {
				fileOut = new FileOutputStream(relativePath);
				obj_out = new ObjectOutputStream(fileOut);
				obj_out.writeObject(obj);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				obj_out.close();

			}

	}


//	public CategoriaDTO loadSavedFile(File file) {
//		if (file.exists()) {
//			ObjectInputStream ois = null;
//			try {
//				ois = new ObjectInputStream(new FileInputStream(file));
//				return (CategoriaDTO) ois.readObject();
//
//			} catch (Exception e) {
//				e.printStackTrace();
//
//			} finally {
//				try {
//					if (ois != null)
//						ois.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return null;
//
//	}



	public String[] listDirectory() {

		return new File(userHome).list();

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
	 * @param filename the file path after the userHome
	 */
	public void deleteFile(String filename) {
		File file = new File(userHome + filename);
		file.delete();
	}

	public void createFolder(String path) {
		new File(userHome+path).mkdir();
		
	}

}