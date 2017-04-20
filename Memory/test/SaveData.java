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

	public void saveData(Object o) throws IOException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(o);
		out.close();
	}

	public Object getData() throws IOException, ClassNotFoundException{
		ObjectInputStream oin = null;
		Object o = null;
		if(this.file.exists()){
			oin = new ObjectInputStream(new FileInputStream(file));
			o = oin.readObject();
			oin.close();
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
