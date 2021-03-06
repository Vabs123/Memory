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
* This class is using TextRazorApi.
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
		client.addExtractor("entities");
		client.addExtractor("phrases");
		client.addExtractor("relations");
		//analyze function for analyzing your text.
		return client.analyze(sentence);
	}

	//ths method returns words that are in relation (subject and objects).
	private ArrayList<Word> getRelationWords(AnalyzedText response){
		ArrayList<Word> words = new ArrayList<>();
		List<Relation> relations = response.getResponse().getRelations();
		if(relations != null){
			for(Relation relation:relations){
				for(RelationParam relParm:relation.getParams())
					for(Word word:relParm.getParamWords())
						words.add(word);
			}
		}
		return words;
	}

	//method returns all predicate and their property words
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

	// method returns all words in noun phrases.
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

	private ArrayList<Word> getAllWords(AnalyzedText response){
		ArrayList<Word> words = new ArrayList<>();
		List<Sentence> sentences = response.getResponse().getSentences();
		if(sentences != null){
			for(Sentence sentence:sentences){
				for(Word word:sentence.getWords()){
					if(!word.getStem().equals("."))
						words.add(word);
				}
			}
		}
		return words;
	}

	

	// method return words that matched with a given word
	private Word getMatchedWords(String word, ArrayList<Word> words){
		for(Word w:words){
			if(w.getStem().equals(word))
				return w;
		}
		return null;
	}

	// method removes duplicate from wordlist
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

	//method removes the stop words using WordDictionary class
	private ArrayList<Word> removeStopWords(ArrayList<Word> words){
		WordDictionary wd = DictionaryManager.getDictionary("StopWord");
		ArrayList<Word> filterWords = new ArrayList<>();
		for(Word word:words){
			if(!wd.containWord(word.getStem()))
				filterWords.add(word);
		}
		return filterWords;
	}

	// returns filtered word.
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
		if(words.size() == 0){
			words.addAll(getAllWords(response));
		}
		words = removeDuplicates(words);
		words = removeStopWords(words);
		return words;
	}

	public static void main(String[] args) {
		String s = "My hostel wifi password is jaggerbery.";
		for(Word word:new NLPImplementation().getWords(s))
			System.out.println(word.getStem());
	}

}