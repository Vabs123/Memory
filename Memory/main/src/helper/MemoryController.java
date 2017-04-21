package helper;

import datasaver.SaveData;
import wordstructure.Memory;
import java.io.File;
import java.util.ArrayList;

public class MemoryController {
	private SaveData sd;
	private ArrayList<Memory> memoryList;

	public MemoryController(){
		this.sd = new SaveData("/tmp/data/memory.txt");
		this.memoryList = (ArrayList<Memory>) sd.getData();
		if(this.memoryList == null)
			this.memoryList = new ArrayList<>();
	}

	public void saveMemory(Memory memory){
		this.memoryList.add(memory);
		this.sd.saveData(memoryList);
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
	public static void main(String[] args) {
		MemoryController memoryController = new MemoryController();
		System.out.println(memoryController.clearMemory());
	}
}