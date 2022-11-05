package Kakao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Intern2019_2 {

	public static void main(String[] args) {
		int[] result = solution("{{1,2,3},{2,1},{1,2,4,3},{2}}");
		for(int i : result)
			System.out.print(i + " ");
	}

    static int[] solution(String s) {
        ArrayList<ArrayList<Character>> list = new ArrayList<>();
        ArrayList<Character> tmp = new ArrayList<>();
        
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c == '}') {
                list.add(new ArrayList<>(tmp));
                tmp.clear();
            }
            if(c == '{' || c == '}' || (c == ',' && s.charAt(i - 1) == '}'))
                continue;
            tmp.add(c);
        }
        
        list.remove(list.size() - 1);
        list.sort((o1, o2) -> o1.size() - o2.size());
        
        HashMap<Integer, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        
        for(ArrayList<Character> l : list) {
        	for(char c : l) {
        		if(c == ',') {
        			int i = Integer.parseInt(sb.toString());
        			if(!map.containsKey(i))
        				map.put(i, idx++);
        			sb.setLength(0);
        			continue;
        		}
        		sb.append(c);
        	}
        	int i = Integer.parseInt(sb.toString());
            if(!map.containsKey(i))
    			map.put(i, idx++);
            sb.setLength(0);
        }
        
        // map에 넣은 순서(value)에 따라 정렬
        List<Map.Entry<Integer, Integer>> entries =
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toList());
        
        int[] ans = new int[idx];
        
        idx = 0;
        for (Map.Entry<Integer, Integer> entry : entries) 
        	ans[idx++] = entry.getKey();
        
        return ans;
    }
}
