package datasaver;

/********************************************************
* This class is used to get dictionary of given tag.
********************************************************/

public class DictionaryManager{
	
	public static WordDictionary getDictionary(String tag){
		SaveData sd = null;
		if(tag.equals("Question"))
			sd = new SaveData("questions.txt");
		else if(tag.equals("StopWord"))
			sd = new SaveData("stop_word.txt");
		return (WordDictionary) sd.getData();
	}
}