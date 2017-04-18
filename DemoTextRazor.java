import com.textrazor.TextRazor;
import com.textrazor.annotations.Entity;
import com.textrazor.annotations.AnalyzedText;

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
		//analyze function for analyzing your text.
		AnalyzedText response = client.analyze("My hostel wifi password is vabro.");
		for (Entity entity : response.getResponse().getEntities()) {
    		System.out.println("Matched Entity: " + entity.getEntityId());	
		}
	}
}