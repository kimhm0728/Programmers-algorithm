package CodingTestKit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hash5 {

	public static void main(String[] args) {
		String[] g = {"classic", "pop", "classic", "classic", "pop"};
		int[] p = {500, 600, 150, 800, 2500};
		
		int result[] = solution(g, p);
		
		for(int i : result)
			System.out.print(i + " ");
	}

    static int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> gMap = new HashMap<>();
        
        for(int i=0;i<genres.length;i++) 
            gMap.put(genres[i], gMap.getOrDefault(genres[i], 0) - plays[i]);
        
        List<Map.Entry<String, Integer>> entries = gMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
        
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<int[]> pList;
        
        for(Map.Entry<String, Integer> entry : entries) {
            pList = new ArrayList<>();
            
            for(int i=0;i<genres.length;i++) {
                if(genres[i].equals(entry.getKey())) 
                    pList.add(new int[] {plays[i], i});
            }
            
            pList.sort((o1, o2) -> o2[0] - o1[0]);
            
            result.add(pList.get(0)[1]);
            if(pList.size() > 1)
                result.add(pList.get(1)[1]);
            
        }
        
        int[] ans = new int[result.size()];
        for(int i=0;i<ans.length;i++) 
            ans[i] = result.get(i);
        
        return ans;
    }
}