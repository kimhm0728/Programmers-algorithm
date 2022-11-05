package Kakao;

public class Intern2019_3_1 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {2,4,5,3,2,1,4,2,5,1}, 3));
	}
	
    static int solution(int[] stones, int k) {
    	int ans = 0;
        int min = 1, max = 200000000;
        
        // 이분 탐색
        while(min<=max) {
        	int mid = (min + max) / 2;
        	if(isToCross(mid, stones, k)) {
        		ans = Math.max(ans, mid);
        		min = mid + 1;
        	}
        	else
        		max = mid - 1;
        }
        
        return ans;
    }
    
    static boolean isToCross(int people, int[] arr, int k) {
    	int num = 0;

    	for(int i : arr) {
    		if(i - people < 0) 
    			num++; // 밟지 못하는 칸이 얼만큼 연속되는지 카운트
    		else 
    			num = 0;
    		if(k == num) // 한 번에 건널 수 있는 칸과 같으면 못 건너는 것 
    			return false;
    	}
    	return true;
    }

}
