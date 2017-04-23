package datasaver;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

/*******************************************************************
* This class is used for serializing and deserializing objects.
*******************************************************************/

public class SaveData {
	private File file;

	// name of file where to save or retrieve objects.
	public SaveData(String file){
		 this.file = new File(getClass().getResource("/data/"+file).getFile());
		//this.file = new File(file);
	}

	public void saveData(Object o) {
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(o);
			out.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public Object getData(){
		ObjectInputStream oin = null;
		Object o = null;
		try{
			if(this.file.exists() && this.file.length() != 0){				
				oin = new ObjectInputStream(new FileInputStream(file));
				o = oin.readObject();
				oin.close();
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return o;
	}

	public File getFile(){
		return this.file;
	}

	public boolean deleteFile(){
		return this.file.delete();
	}

}
