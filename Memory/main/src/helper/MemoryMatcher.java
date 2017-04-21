package helper;

import java.util.ArrayList;
import wordstructure.Memory;
import wordstructure.WordInfo;

public class MemoryMatcher{
	private ArrayList<Memory> memoryList;
	private Memory matchedMemory;
	private ArrayList<WordInfo> notMatchedWords;
	
	public MemoryMatcher(Memory memory){
		this.notMatchedWords = new ArrayList<>();
		this.memoryList = new MemoryController().getMemories();
		this.matchedMemory = matchMemory(memory);
		findNotMatchedWords(memory);
	}


	private boolean containsWord(WordInfo word, Memory memory){
		for(WordInfo wordInfo:memory.getWordList()){
			if(wordInfo.getWord().equals(word.getWord()))
				return true;
		}
		return false;
	}

	private int getScore(Memory mem1, Memory mem2){
		int score = 0;
		for(WordInfo wordInfo:mem1.getWordList()){
			if(containsWord(wordInfo,mem2))
				score++;
		}
		return score;
	}

	private Memory matchMemory(Memory memory){
		Memory matchingMemory = null;
		int maxScore = 0,score = 0;
		for(Memory mem:memoryList){
			score = getScore(memory, mem);
			if(maxScore < score){
				maxScore = score;
				matchingMemory = mem;
			}
		}
		return matchingMemory;
	}

	private void findNotMatchedWords(Memory memory){
		for(WordInfo word:this.matchedMemory.getWordList()){
			if(!containsWord(word, memory))
				this.notMatchedWords.add(word);
		}
	}

	public ArrayList<WordInfo> getNotMatchedWords(){
		return this.notMatchedWords;
	}

	public Memory getMatchedMemory(){
		return this.matchedMemory;
	}
}