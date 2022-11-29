package Kakao2018;

public class Level1_1_2 {
	// 2018 KAKAO BLIND RECRUITMENT 1차 Level 1 두 번째 문제 비밀지도

	public static void main(String[] args) {
		String[] result = solution(6, new int[] {46, 33, 33, 22, 31, 50}, 
				new int[] {27, 56, 19, 14, 14, 10});
		
		for(String s : result)
			System.out.println(s);
	}
	
	static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] ans = new String[n];
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<n;i++) {
            String temp = Integer.toBinaryString(arr1[i] | arr2[i]);
            
            sb.append(" ".repeat(n - temp.length()));
            for(char c : temp.toCharArray())
                if(c == '0')
                    sb.append(' ');
                else
                    sb.append('#');
            ans[i] = sb.toString();
            sb.setLength(0);
        }
        
        return ans;
    }

}
