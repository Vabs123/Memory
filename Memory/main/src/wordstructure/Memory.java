package wordstructure;

import java.util.ArrayList;
import java.io.Serializable;

/******************************************************************
* This class represent the text form that will be saved by bot.
* wordList - the collection of WordInfo Objects;
* sentence - the complete text enter by the user.
******************************************************************/ 

public class Memory implements Serializable{
	private static final long serialVersionUID = 2L;
	private ArrayList<WordInfo> wordInfoList;
	private String sentence;

	public Memory(){
		this.wordInfoList = new ArrayList<>();
		this.sentence = "";
	}

	public int getSentenceLength(){
		return this.sentence.length();
	}

	public int getWordInfoListSize(){
		return wordInfoList.size();
	}

	public ArrayList<WordInfo> getWordList(){
		return wordInfoList;
	}

	public void insertWordInfo(WordInfo wordInfo){
		this.wordInfoList.add(wordInfo);
	}


	public WordInfo getWordInfoAt(int index){
		return this.wordInfoList.get(index);
	}

	public void setSentence(String sentence){
		this.sentence = sentence;
	}

	public String getSentence(){
		return this.sentence;
	}

	// WordInfo contains word stem, not the original word.
	// This function will give the orignal word as entered by the user 
	// by matching the WordInfo object starting and ending index with
	// original word. 
	public String getWordAt(int startIndex, int endIndex){
		String word = "";
		for(int i = startIndex; i < endIndex; i++)
			word += this.sentence.charAt(i);
		return word;
	}

	@Override
	public String toString(){
		return this.wordInfoList.toString();
	}
}