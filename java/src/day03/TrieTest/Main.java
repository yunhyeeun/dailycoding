package day03.TrieTest;

public class Main {
    
    public static void main (String[] args) {

    }

}

class Trie {
    TrieNode root = new TrieNode();

    void insert(String word) {
        TrieNode current = root;
        for (int i = 0;i < word.length();i++) {
            char c = word.charAt(i);
            if (current.hasChild(c) == false) {
                current.children[c - 'A'] = new TrieNode();
            }
            current = current.getChild(c);
        }
        current.isEnd = true;
    }

    boolean checkWord(String word) {
        TrieNode current = root;
        for (int i = 0;i < word.length();i++) {
            char c = word.charAt(i);
            if (current.hasChild(c)) {
                current = current.getChild(c);
            } else {
                return false;
            }
        }
        return current.isEnd;
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
    boolean isHit;
    
    TrieNode getChild(char c) {
        return children[c - 'A'];
    }

    boolean hasChild(char c) {
        return children[c - 'A'] != null;
    }
}