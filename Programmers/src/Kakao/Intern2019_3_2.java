package Kakao;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

public class Intern2019_3_2 {
	static int ans = 0;
	static ArrayList<Integer[]> all = new ArrayList<>();
	
	public static void main(String[] args) {
		String[] u = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] b = {"fr*d*", "*rodo", "******", "******"};
		
		System.out.println(solution(u, b));
	}

    static int solution(String[] user_id, String[] banned_id) {
    	ArrayList<Integer>[] loc = new ArrayList[banned_id.length];
    	ArrayList<Integer>[] mapping = new ArrayList[banned_id.length];

    	for(int i=0;i<loc.length;i++) {
        	loc[i] = new ArrayList<>();
        	mapping[i] = new ArrayList<>();
        }
    	
        // 각 문자열의 * 위치 저장
        for(int i=0;i<banned_id.length;i++) {
        	for(int j=0;j<banned_id[i].length();j++)
        		if(banned_id[i].charAt(j) == '*')
        			loc[i].add(j);
        }
        
        for(int i=0;i<banned_id.length;i++) {
        	for(int j=0;j<user_id.length;j++) {
        		if(banned_id[i].length() == user_id[j].length() && mappingChk(loc[i], banned_id[i], user_id[j]))
        			mapping[i].add(j);
        	}
        }
        
        HashSet<Integer> set = new HashSet<>();
        boolean visit[][] = new boolean[user_id.length][user_id.length];
        DFS(set, mapping, visit, 0, banned_id.length);
    
        set.clear();
        for(Integer[] arr : all) {
        	Arrays.sort(arr);
        	StringBuilder sb = new StringBuilder();
        	for(int i : arr)
        		sb.append(i);
        	set.add(Integer.parseInt(sb.toString()));
        }
        
        return set.size();
    }
    
    static void DFS(HashSet<Integer> set, ArrayList<Integer>[] mapping, boolean[][] visit, int idx, int cnt) {
    	if(idx == cnt) {
    		ans++;
    		all.add(set.toArray(new Integer[0]));
    		return;
    	}
    	
    	for(int i=0;i<mapping[idx].size();i++) {
    		if(!visit[idx][i]) {
    			int num = mapping[idx].get(i);
    			if(set.contains(num)) continue;
    			
    			visit[idx][i] = true;
    			set.add(num);
    			DFS(set, mapping, visit, idx + 1, cnt);
    			visit[idx][i] = false;
    			set.remove(mapping[idx].get(i));
    		}
    	}
    	
    }
    
    // 불량 사용자 * 위치에 *로 교체 후 같다면 매핑 가능
    static boolean mappingChk(ArrayList<Integer> list, String banId, String userId) {
    	StringBuilder sb = new StringBuilder(userId);
    	 
    	for(int i : list)
    		sb.setCharAt(i, '*');
        
    	if(sb.toString().equals(banId))
    		return true;
    	
    	return false;
    }
 
}
