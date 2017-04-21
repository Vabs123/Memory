package nlp;

import com.textrazor.TextRazor;
import com.textrazor.annotations.Entity;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Word;
import com.textrazor.annotations.Sentence;
import com.textrazor.annotations.Relation;
import com.textrazor.annotations.RelationParam;
import com.textrazor.annotations.Property;
import com.textrazor.annotations.NounPhrase;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import datasaver.WordDictionary;
import datasaver.DictionaryManager;
/************************************************
* This is demo of using TextRazorApi.
* The TextRazor API helps you extract 
* and understand the Who, What, Why and 
* How from your text with unprecedented 
* accuracy and speed.
************************************************/

public class NLPImplementation{
	//TextRazor api key 
	private static String API_KEY = "e76b65f4aa6b75401ad3672a0e128e9c6edf49bc4aa535aa2ea1a17f"; 

	private AnalyzedText getTextResponse(String sentence) throws Exception{
		//Creating object of TextRazor using api key.
		TextRazor client = new TextRazor(API_KEY);
		//addExtractor will be used to get specific parts of speech text. 
		client.addExtractor("words");
		client.addExtractor("phrases");
		client.addExtractor("relations");
		//analyze function for analyzing your text.
		return client.analyze(sentence);
	}

	private ArrayList<Word> getRelationWords(AnalyzedText response){
		ArrayList<Word> words = new ArrayList<>();
		List<Relation> relations = response.getResponse().getRelations();
		if(relations != null){
			for(Relation relation:relations){
				//for(Word word:relation.getPredicateWords())
				//	words.add(word);
				for(RelationParam relParm:relation.getParams())
					for(Word word:relParm.getParamWords())
						words.add(word);
			}
		}
		return words;
	}

	private ArrayList<Word> getPropWords(AnalyzedText response){
		ArrayList<Word> words = new ArrayList<>();
		List<Property> properties = response.getResponse().getProperties();
		if(properties != null){
			for(Property prop:properties){
				for(Word word:prop.getPredicateWords())
					words.add(word);
				for(Word word:prop.getPropertyWords())
					words.add(word);
			}
		}
		return words;
	}

	private ArrayList<Word> getNounPhraseWords(AnalyzedText response){
		ArrayList<Word> words = new ArrayList<>();
		List<NounPhrase> nounPhrases = response.getResponse().getNounPhrases();
		if(nounPhrases != null){
			for(NounPhrase nounPhrase:nounPhrases){
				for(Word word:nounPhrase.getWords())
					words.add(word);
			}
		}
		return words;
	}

	private Word getMatchedWords(String word, ArrayList<Word> words){
		for(Word w:words){
			if(w.getStem().equals(word))
				return w;
		}
		return null;
	}

	private ArrayList<Word> removeDuplicates(ArrayList<Word> words){
		ArrayList<Word> uniqueWords = new ArrayList<>();
		LinkedHashSet<String> set = new LinkedHashSet<>();

		for(Word word:words){
			set.add(word.getStem());
		}

		for(String word:set){
			uniqueWords.add(getMatchedWords(word, words));
		}
		return uniqueWords;
	}

	private ArrayList<Word> removeStopWords(ArrayList<Word> words){
		WordDictionary wd = DictionaryManager.getDictionary("StopWord");
		ArrayList<Word> filterWords = new ArrayList<>();
		for(Word word:words){
			if(!wd.containWord(word.getStem()))
				filterWords.add(word);
		}
		return filterWords;
	}

	public ArrayList<Word> getWords(String sentence){
		ArrayList<Word> words = new ArrayList<>();
		AnalyzedText response = null;
		try{
			response = getTextResponse(sentence);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		if(response != null){
			words.addAll(getNounPhraseWords(response));
			words.addAll(getPropWords(response));
			words.addAll(getRelationWords(response));
		}
		words = removeDuplicates(words);
		words = removeStopWords(words);
		return words;
	}

	public static void main(String[] args) {
		String s = "I have a meeting in my office at 2 pm.";
		for(Word word:new NLPImplementation().getWords(s))
			System.out.println(word.getStem());
	}

}