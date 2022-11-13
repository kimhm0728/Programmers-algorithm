package CodingTestKit;

import java.util.HashSet;

public class Hash2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new int[] {3,1,2,3}));
	}

    public static int solution(int[] nums) {
        int answer = 0;
        
        HashSet<Integer> h = new HashSet<Integer>();
        for(int n : nums) h.add(n);
        
        answer = h.size() < nums.length/2 ? h.size() : nums.length/2;
        
        return answer;
    }
    
}
