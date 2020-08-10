package day01.p1062;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N, K;
	static String[] words;
	static boolean[] visited;
	static int selectedCount;
	static int max = 0;
	
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("java/src/day01/p1062/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = Integer.parseInt(sc.next());
		K = Integer.parseInt(sc.next());
				
		words = new String[N];
        visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;
        
        for (int i = 0; i < N; i++) {
			words[i] = sc.next().replaceAll("[antic]", "");
		}
        
        if (K < 5) {
        	System.out.println(max);
        } else {
        	if (K >= 26) {
        		System.out.println(N);
        	} else {
        		selectedCount = 5;
                max = countWords();
                
        		for (int i = 0; i < 26; i++) {
        			if (!visited[i]) {
        				dfs(i);
        			}
        		}
                System.out.println(max);
        	}
        }
        
    }
    
    static void dfs(int index) {
    	// 1. 체크인
    	visited[index] = true;
    	selectedCount++;
    	// 2. 목적지인가?
    	if (selectedCount == K) {
    		max = Math.max(countWords(), max);
    	} else {
    		// 3. 갈 수 있는 곳을 순
    		for (int i = index + 1;i < 26;i++) {
    			// 4. 갈 수 있는가?
	    		if (visited[i] == false) {
	    			// 5. 간다
	    			dfs(i);
	    		}	
    		}
    	}
    	// 6. 체크아웃 
    	visited[index] = false;
    	selectedCount--;
    }
    
    static int countWords() {
    	int count = 0;
    	for (int i = 0;i < N;i++) {
    		boolean flag = true;
    		String word = words[i];
    		for (int j=0;j<word.length();j++) {
    			if (!visited[word.charAt(j) - 'a']) {
    				flag = false;
    				break;
    			}
    		}
    		if (flag) {
    			count++;
    		}
    	}
    	return count;
    }

}
