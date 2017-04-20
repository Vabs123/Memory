import java.util.ArrayList;

class ScoreCalculator{
	private ArrayList<Memory> memoryList;

	public ScoreCalculator(){
		this.memoryList = new MemoryController().getMemories();
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

	public Memory getMatchingMemory(Memory memory){
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
}