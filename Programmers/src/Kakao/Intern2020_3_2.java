package Kakao;

import java.util.HashSet;
import java.util.HashMap;

public class Intern2020_3_2 {

	public static void main(String[] args) {
		int[] arr = solution(new String[] {"A","B","A","A","A","A","C","B"});
		System.out.println(arr[0] + " " + arr[1]);
	}

	static int[] solution(String[] gems) {
        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        
		for(String s : gems)
        	set.add(s);
		
		int start = 0, end = 0;
		int ans1 = 0, ans2 = 100000;
		
		if(map.size() == 1)
			return new int[] {1, 1};
		
		while(true) {
			if(set.size() == map.size()) {
				if(ans2 - ans1 > end - (start + 1)) {
					ans1 = start + 1;
					ans2 = end;
				}
				
				int i = map.get(gems[start]);
				if(i - 1 == 0)
					map.remove(gems[start]);
				else
					map.put(gems[start], i - 1);
				start++;
			} 
			else {
				if(end >= gems.length)
					break;
				map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
				end++;
			}
		}
		int[] answer = {ans1, ans2};
        return answer;
    }
}
