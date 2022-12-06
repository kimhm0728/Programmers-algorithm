package Kakao2019;

import java.util.*;

public class Level2_1 {

	public static void main(String[] args) {
		String[] result = solution(new String[] {"Enter uid1234 Muzi", 
				"Enter uid4567 Prodo",
				"Leave uid1234",
				"Enter uid1234 Prodo",
				"Change uid4567 Ryan"});
		
		for(String s : result)
			System.out.println(s);
	}

	static String[] solution(String[] record) {
        StringTokenizer st;
        HashMap<String, String> map = new HashMap<>();
        int change = 0;
        
        for(String str : record) {
            st = new StringTokenizer(str);
            String state = st.nextToken();
            
            if(!state.equals("Leave")) {
                if(state.equals("Change"))
                    change++;
                
                String id = st.nextToken();
                String nickname = st.nextToken();
                
                map.put(id, nickname);
            }
        }
        
        String[] ans = new String[record.length - change];
        int idx = 0;
        
        for(String str : record) {
            st = new StringTokenizer(str);
            
            String state = st.nextToken();
            
            if(state.equals("Change"))
                continue;
            
            String id = st.nextToken();
            StringBuilder sb = new StringBuilder();
            
            // 가장 마지막으로 변경한 닉네임
            sb.append(map.get(id));
            
            if(state.equals("Enter")) 
                sb.append("님이 들어왔습니다.");
            else 
                sb.append("님이 나갔습니다.");
            
            ans[idx++] = sb.toString();
        }

        return ans;
    }
}
