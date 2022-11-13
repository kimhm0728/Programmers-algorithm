package Kakao;

public class Intern_2022_Winter_1 {
	static int ans = -1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(1, 5, 2));
	}
	
    public static int solution(int x, int y, int z) {
    // Write your code here
    	DFS(x, y, z, x);
 
    	return ans;
    }
    
    static void DFS(int start, int end, int cnt, int max) {
    	if(start == end)
    		ans = Math.max(max, ans);
    	
    	if(cnt == 0) 
    		return;

    	DFS(start + 1, end, cnt - 1, max + 1);
    	DFS(start - 1, end, cnt - 1, max);
    }

}
