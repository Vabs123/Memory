package datasaver;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class SaveData {
	private File file;

	public SaveData(String file){
		this.file = new File(file);
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
			if(this.file.exists()){				
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
