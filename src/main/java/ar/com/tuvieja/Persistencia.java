package ar.com.tuvieja;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import ar.com.SnippletServer.dto.SendDTO;



public class Persistencia {


	private String userHome;
	
	
	public Persistencia(){
		userHome = System.getProperty("user.home") + "\\"+"SnippletServer"+"\\";
		
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
		System.out.println("absolute path: "+userHome+filename);
		return new File(userHome + filename).exists();

	}
	
	

	public String save(SendDTO sendDTO) throws IOException {

		String relativePath = sendDTO.getUsername()+"\\"+ sendDTO.getCategoriaDTO().getNombre();
		System.out.println("relative path: "+relativePath);
		if(!fileExist(sendDTO.getUsername())){
			System.out.println("folder created: "+sendDTO.getUsername());
			createFolder(sendDTO.getUsername());
		}
		
		
		if (fileExist(relativePath)) {
			System.out.println("file exist?: "+relativePath);
			deleteFile(relativePath);
		}
			FileOutputStream fileOut;
			ObjectOutputStream obj_out = null;
			try {
				fileOut = new FileOutputStream(userHome+relativePath);
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