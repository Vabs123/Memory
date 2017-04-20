import java.io.File;
import java.util.ArrayList;

public class MemoryController {
	SaveData sd;
	private ArrayList<Memory> memoryList;

	public MemoryController(){
		try{
			this.sd = new SaveData("memory.txt");
			this.memoryList = (ArrayList<Memory>) sd.getData();
		}
		catch(Exception e){
			System.out.println(e);
		}
		if(this.memoryList == null)
			this.memoryList = new ArrayList<>();
	}

	public void saveMemory(Memory memory){
		this.memoryList.add(memory);
		try{
			this.sd.saveData(memoryList);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public ArrayList<Memory> getMemories(){
		return this.memoryList;
	}

	public boolean clearMemory(){
		this.memoryList = new ArrayList<>();
		return sd.deleteFile();
	}

	@Override
	public String toString(){
		return this.memoryList.toString();
	}

}