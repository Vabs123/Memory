package run;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Random;

import com.textrazor.annotations.Word;

import wordstructure.Memory;
import wordstructure.WordInfo;
import datasaver.DictionaryManager;
import datasaver.WordDictionary;
import nlp.NLPImplementation;
import helper.MemoryMatcher;
import helper.MemoryController;

public class AnswerQuery{
	
	private boolean isQuestion(String sentence){
		sentence = sentence.toLowerCase();
		String[] helpingVerbs = {"am","are","is","was","were","be","being","been",
		 "have","has","had","shall", "will", "do", "does", "did","may", "must",
 		"might","can", "could", "would", "should "};
		StringTokenizer st = new StringTokenizer(sentence);
		WordDictionary wordDictionary = DictionaryManager.getDictionary("Question");
		while(st.hasMoreTokens()){
			if(wordDictionary.containWord(st.nextToken()))
				return true;
		}
		st = new StringTokenizer(sentence);
		String s1 = st.nextToken();
		for(String s:helpingVerbs){
			if(s.equals(s1))
				return true;
		}
		return false;		
	}

	private Memory createMemory(String sentence){
		Memory memory = new Memory();
		WordInfo wordInfo = null; 
		ArrayList<Word> words = new NLPImplementation().getWords(sentence);
		for(Word word:words){
			memory.insertWordInfo(new WordInfo(word.getStartingPos(), word.getEndingPos(), word.getStem(), ""));
			memory.setSentence(sentence);
		}
		return memory;
	}

	private ArrayList<String> findAnswer(Memory memory){
		ArrayList<String> answer = new ArrayList<>();
		MemoryMatcher memoryMatcher = new MemoryMatcher(memory);
		Memory mem = memoryMatcher.getMatchedMemory();
		for(WordInfo word:memoryMatcher.getNotMatchedWords()){
			answer.add(mem.getWordAt(word.getStartIndex(), word.getEndIndex()));
		}
		return answer;
	}

	private void takeInput() throws Exception{
		String response = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("> ");
		String sentence = br.readLine();
		Memory memory = createMemory(sentence);
		if(isQuestion(sentence)){
			getQuestionResponse(findAnswer(memory));
		}
		else{
			new MemoryController().saveMemory(memory);
			getInputResponse();
		}
	}

	private void getInputResponse(){
		final String[] responses = {"Remembered...","Got it...", "Digested it... Bring me more, I am hungry!", "Added in my memory bank successfully.", "Noted down Sir."};
		System.out.println("Memory- "+responses[new Random().nextInt(responses.length)]);
	}

	private void getQuestionResponse(ArrayList<String> words){
		String response = "";
		for(String word:words)
			response += " "+word;
		final String[] responses = {"Here: "+response,"This is what I found:\n"+response, "This is what you want, Isn't it?:\n"+response, "I remembered that, found it:\n"+response};
		System.out.println("Memory- "+responses[new Random().nextInt(responses.length)]);
	}

	public static void main(String[] args) throws Exception {
		System.out.print("\033[H\033[2J");
		System.out.println();
		System.out.println("*******************************************************************************");
		System.out.println("-------------------------------MEMORY BOT--------------------------------------");
		System.out.println("*******************************************************************************");
		System.out.println();
		System.out.print("Memory- Hello Sir, My name is Memory, I can assist you by remembering all your important ");
		System.out.println("dates, meetings, deadlines and anything you want, so that you can remain free of mind.");
		System.out.println("- Tell me whatever you want me to remember.");
		AnswerQuery answerQuery = new AnswerQuery();
		while(true){
			try{
				answerQuery.takeInput();
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Oops missed try again please!");
			}
		}
	}

}