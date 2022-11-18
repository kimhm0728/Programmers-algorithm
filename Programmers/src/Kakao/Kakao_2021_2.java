package Kakao;

import java.util.*;

public class Kakao_2021_2 {
	static HashMap<String, Integer> map = new HashMap<>();
	static ArrayList<String> result = new ArrayList<>();
	static int max;
	
	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2, 3, 4};
		
		String[] result = solution(orders, course);
		
		for(String s : result)
			System.out.println(s);
	}
	
    static String[] solution(String[] orders, int[] course) {
        // 메뉴 오름차순 정렬
    	for(int i=0;i<orders.length;i++) {
    		char[] c = orders[i].toCharArray();
    		Arrays.sort(c);
    		orders[i] = new String(c);
    	}
    	
    	for(int i=0;i<course.length;i++) {
    		map.clear();
    		max = 0;
    		for(int j=0;j<orders.length;j++) 
    			DFS(orders[j].toCharArray(), 0, course[i], 0, "");
    		
    		findCourse();
    	}
    	
    	String[] ans =  result.toArray(new String[0]);
    	Arrays.sort(ans);
    	
    	return ans;
    }
    
    static void DFS(char[] c, int start, int course, int depth, String s) {
    	if(c.length < course)
    		return;
    	if(depth == course) {
    		// 가장 많이 주문된 메뉴 찾기
    		max = Math.max(max, map.getOrDefault(s, 0) + 1);
    		map.put(s, map.getOrDefault(s, 0) + 1);
    		return;
    	}
    	
    	// 구성할 메뉴의 갯수만큼 조합
    	for(int i=start;i<c.length;i++) 
    		DFS(c, i + 1, course, depth + 1, s + c[i]);
  
    }
    
    static void findCourse() {
    	if(max < 2)
    		return;
    	
    	for(String s : map.keySet()) {
    		if(map.get(s) == max)
    			result.add(s);
    	}
    }

}
