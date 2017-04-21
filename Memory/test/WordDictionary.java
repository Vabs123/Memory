import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.*;

public class WordDictionary implements Serializable{

    private class TrieNode implements Serializable{
        Map<Character, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    /**
     * Iterative implementation of insert into trie
     */
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        //mark the current nodes endOfWord as true
        current.endOfWord = true;
    }

    public boolean containWord(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            //if node does not exist for given char then return false
            if (node == null) {
                return false;
            }
            current = node;
        }
        //return true of current's endOfWord is true else return false.
        return current.endOfWord;
    }

    public static void main(String[] args) throws Exception{
       /* WordDictionary wordDictionary = new WordDictionary();
        HashSet<String> words = new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader("question.txt"));
        String s ="";
        while((s = br.readLine()) != null)
            words.add(s);
        for(String word:words)
            wordDictionary.insert(word);*/
        SaveData sd = new SaveData("stop_word.txt");
       // sd.saveData(wordDictionary);
        WordDictionary wd = (WordDictionary) sd.getData();
       // wd.insert("remember");
        System.out.println(wd.containWord("remember"));
        //sd.saveData(wd);
    }
}