package day03.p9202;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int w, b;
    static String words[];
    static char[][] board;
    static Trie trie;
    static boolean visited[][];
    static StringBuilder sb;
    static String word = "";
    static String answer = "";
    static HashSet<String> wordSet;
    static int[] scoreBoard = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    // 상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
    static int[] mx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] my = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        
        System.setIn(new FileInputStream("./src/day03/p9202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // word dictionary
        w = Integer.parseInt(st.nextToken());
        words = new String[w];
        trie = new Trie();
        for (int i = 0;i < w;i++) {
            st = new StringTokenizer(br.readLine());
            words[i] = st.nextToken();
            trie.insert(words[i]);
        }

        // board
        br.readLine();
        st = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st.nextToken());

        for (int n = 0;n < b;n++) {
            wordSet = new HashSet<>();
            board = new char[4][4];
            for (int i = 0;i < 4;i++) {
                st = new StringTokenizer(br.readLine());
                board[i] = st.nextToken().toCharArray();
            }
            
            boggle();
            br.readLine();
        }

        
    }

    public static void boggle() {
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                if (trie.root.hasChild(board[i][j])) {
                    visited = new boolean[4][4];
                    sb = new StringBuilder();
                    dfs(i, j, 1, trie.root.getChild(board[i][j]));
                }
                // word = "";
            }
        }
        answer = grade();
        trie.root.clearHit();
        System.out.println(answer);
    }
    public static String grade() {
        String[] wordArray = new String[wordSet.size()];
        wordSet.toArray(wordArray);
        Arrays.sort(wordArray);
        String longest = "";
        int score = 0;
        for (int i = 0;i < wordArray.length;i++) {
            if (wordArray[i].length() > longest.length()) {
                longest = wordArray[i];
            }
            score += scoreBoard[wordArray[i].length()];
        }

        return score + " " + longest + " " + wordArray.length;


    }
    public static void dfs(int row, int col, int current, TrieNode currentNode) {
        visited[row][col] = true;
        sb.append(board[row][col]);
        if (currentNode == null) {
            return;
        }
        if (currentNode.isEnd && currentNode.isHit == false) {
            currentNode.isHit = true;
            wordSet.add(sb.toString());
        }

        for (int i = 0;i < 8;i++) {
            int trow = row + my[i];
            int tcol = col + mx[i];
            if (canMove(trow, tcol)) {
                if (currentNode.hasChild(board[trow][tcol])) {
                    dfs(trow, tcol, current + 1, currentNode.getChild(board[trow][tcol]));
                }
            }
        }
        visited[row][col] = false;
        sb.deleteCharAt(current - 1);
    }

    public static boolean canMove(int row, int col) {
        return row >= 0 && row < 4 && col >= 0 && col < 4 && visited[row][col] == false;
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

    boolean search(String word) {
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

    void clearHit() {
        isHit = false;
        for (int i = 0;i < children.length;i++) {
            if (children[i] != null) {
                children[i].clearHit();
            }
        }
    }
}