package helper;

import datasaver.SaveData;
import wordstructure.Memory;
import wordstructure.WordInfo;

import java.io.File;
import java.util.ArrayList;

/**************************************************************************
* This class acts as a Wrapper on SaveData class. It saves and retrieves 
* memory objects.
**************************************************************************/

public class MemoryController {
	private SaveData sd;
	private ArrayList<Memory> memoryList;

	public MemoryController(){
		this.sd = new SaveData("memory.txt");
		this.memoryList = (ArrayList<Memory>) sd.getData();
		if(this.memoryList == null)
			this.memoryList = new ArrayList<>();
	}

	//method check if particular memory contains given word.
	private boolean containsWord(WordInfo word, Memory memory){
		for(WordInfo words:memory.getWordList()){
			if(word.getWord().equals(words.getWord()))
				return true;
		}
		return false;
	}

	//method compares two memories
	private boolean isSimilar(Memory mem1, Memory mem2){
		for(WordInfo word:mem1.getWordList()){
			if(!containsWord(word,mem2))
				return false;
		}
		return true;
	}

	//method for checking similar memory
	private boolean isAlreadyExist(Memory memory){
		for(Memory mem:this.memoryList){
			if(isSimilar(mem,memory))
				return true;
		}
		return false;
	}

	public boolean saveMemory(Memory memory){
		// to stop saving of duplicate memories
		if(!isAlreadyExist(memory)){
			this.memoryList.add(memory);
			this.sd.saveData(memoryList);
			return true;
		}
		return false;
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