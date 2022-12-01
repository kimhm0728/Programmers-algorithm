package Kakao2018;

public class Level2_3_1 {
	// 2018 KAKAO BLIND RECRUITMENT 3차 Level 2 첫 번째 문제 n진수 게임
	
	public static void main(String[] args) {
		System.out.println(solution(2, 4, 2, 1));
	}
	
	static String solution(int n, int t, int m, int p) {
        StringBuilder ans = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        
        int num = 0;
        int idx = 0;
        int len;
        
        while(true) {
            if(ans.length() >= t)
                break;
            
            sb.append(Integer.toString(num++, n));
            len = sb.length();
            
            if(ans.length() == 0 && len >= p) {
                ans.append(sb.charAt(p - 1));
                idx = p;
            }
            
            while(idx + m < len) {
                idx += m;
                ans.append(sb.charAt(idx - 1));
            }
        }
        
        ans.setLength(t);
        return ans.toString().toUpperCase();
    }

}
