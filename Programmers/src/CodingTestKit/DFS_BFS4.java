package CodingTestKit;

import java.util.Queue;
import java.util.LinkedList;

public class DFS_BFS4 {
	static int result[];

	public static void main(String[] args) {
		System.out.println(solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log"}));
	}
	
    static int solution(String begin, String target, String[] words) {
        return BFS(begin, target, words);
    }

    static int BFS(String begin, String target, String[] words) {
    	result = new int[words.length];
    	Queue<Integer> q = new LinkedList<>();
    	
    	q.offer(-1);
    	
    	while(!q.isEmpty()) {
    		int pre = q.poll();
    		String preStr = pre == -1 ? begin : words[pre];
    		int preResult = pre == -1 ? 0 : result[pre];
    		
    		if(preStr.equals(target))
    			return result[pre];
    		
    		for(int i=0;i<words.length;i++) {
    			if(i != pre && result[i] == 0 && strDiff(words[i], preStr)) {
    				result[i] = preResult + 1;
    				q.offer(i);
    			}
    		}
    	}
    	
    	return 0;
    }
    
    static boolean strDiff(String s1, String s2) {
    	int cnt = 0;
    	
    	for(int i=0;i<s1.length();i++) {
    		if(s1.charAt(i) != s2.charAt(i))
    			cnt++;
    		
    		if(cnt > 1) return false;
    	}
    	
    	return true;
    }
}
