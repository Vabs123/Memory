package datasaver;

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;

import java.io.Serializable;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

/***********************************************************************************
* This class is used to make dictionary of words using Trie Data Structure.
* Trie is used because it searches word in O(length(string)).
* Stop word Dictionary is made using this.
* To check if sentence is question or not.
************************************************************************************/

public class WordDictionary implements Serializable{
    private static final long serialVersionUID = 1L;

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

    public static void main(String[] args) throws Exception {
      /*  HashSet<String> set = new HashSet<>();
        WordDictionary wd = new WordDictionary();
        File f = new File(WordDictionary.class.getClass().getResource("/data/stop_words.txt").getFile());
        BufferedReader br = new BufferedReader(new FileReader(f));
        String s = "";
        while((s = br.readLine()) != null){
            set.add(s);
        }

        for(String ss:set)
            wd.insert(ss);
*/
        SaveData sd = new SaveData("stop_word.txt");
     //   sd.saveData(wd);

       WordDictionary wd = (WordDictionary) sd.getData();
        System.out.println(wd.containWord("name"));
    }
}