package CodingTestKit;

import java.util.HashMap;

public class Hash4 {
    private static int cnt = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new String[][] {
			{"yellow_hat", "headgear"}, 
			{"blue_sunglasses", "eyewear"}, 
			{"green_turban", "headgear"}
			}));
	}
	
    static int solution(String[][] clothes) {
        HashMap<String, Integer> h = new HashMap<String, Integer>();
        
        for(int i=0;i<clothes.length;i++) 
            h.put(clothes[i][1], h.getOrDefault(clothes[i][1], 0) + 1);
        
        h.entrySet().stream()
            .forEach(type -> cnt *= (type.getValue() + 1));
        
        return cnt - 1;
    }

}
