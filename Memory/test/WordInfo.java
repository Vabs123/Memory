import java.io.Serializable;

public class WordInfo implements Serializable{
	private int startIndex;
	private int endIndex;
	private String word;
	private String category;

	public void setStartIndex(int index){
		this.startIndex = index;
	}

	public void setEndIndex(int index){
		this.endIndex = index;
	}

	public void setWord(String word){
		this.word = word;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public int getStartIndex(){
		return this.startIndex;
	}

	public int getEndIndex(){
		return this.endIndex;
	}

	public String getWord(){
		return this.word;
	}

	public String getCategory(){
		return this.category;
	}

	@Override
	public String toString(){
		return this.word;
	}
}