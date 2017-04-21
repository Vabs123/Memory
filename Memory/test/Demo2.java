//package test;

import com.textrazor.TextRazor;
import com.textrazor.annotations.Entity;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Word;
import com.textrazor.annotations.Sentence;
import java.util.List;
import com.textrazor.annotations.Relation;
import com.textrazor.annotations.RelationParam;
import com.textrazor.annotations.Property;
/************************************************
* This is demo of using TextRazorApi.
* The TextRazor API helps you extract 
* and understand the Who, What, Why and 
* How from your text with unprecedented 
* accuracy and speed.
************************************************/

class DemoTextRazor{
	//TextRazor api key 
	static String API_KEY = "e76b65f4aa6b75401ad3672a0e128e9c6edf49bc4aa535aa2ea1a17f"; 

	public static void main(String[] args) throws Exception {
		//Creating object of TextRazor using api key.
		TextRazor client = new TextRazor(API_KEY);
		//addExtractor will be used to get specific parts of speech text. 
		client.addExtractor("words");
		client.addExtractor("entities");
		client.addExtractor("relations");
		
		//analyze function for analyzing your text.
		AnalyzedText response = client.analyze("My hostel wifi password is vabs.");

		/*for (Entity entity : response.getResponse().getEntities()) {
    		System.out.println("Matched Entity: " + entity.getEntityId());	
		}*/
		/*for(Relation relation:response.getResponse().getRelations()){
			for(Word word1:relation.getPredicateWords()){
				System.out.println("Predicate = "+word1.getLemma());
			}
			for(RelationParam relPar:relation.getParams()){
				for(Word word:relPar.getParamWords()){
					System.out.println("Parameters = "+word.getLemma());
				}
			}
		}
				//System.out.println(relPar.getRelation());*/
		for(Property prop:response.getResponse().getProperties()){
				for(Word word:prop.getPredicateWords()){
					System.out.println("Predicate ->"+word.getLemma());						
				}
				for(Word word:prop.getPropertyWords()){
					System.out.println("Property ->"+word.getLemma());						
				}
				System.out.println("--------------------------------");
			}
	}
}