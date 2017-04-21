package datasaver;

public class DictionaryManager{
	
	public static WordDictionary getDictionary(String tag){
		SaveData sd = null;
		if(tag.equals("Question"))
			sd = new SaveData("/tmp/data/questions.txt");
		else if(tag.equals("StopWord"))
			sd = new SaveData("/tmp/data/stop_word.txt");
		return (WordDictionary) sd.getData();
	}
}