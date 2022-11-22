package Kakao;

public class Kakao2020_2_1 {

	public static void main(String[] args) {
		System.out.println(solution("ababcdcdababcdcd"));
	}

	static int solution(String s) {
		int len = s.length();
		int ans = s.length();

		// 1부터 문자열의 길이의 반까지의 단위로 잘라 압축
		for(int i=1;i<=s.length() / 2;i++) {
			StringBuilder sb = new StringBuilder();
			String delim = s.substring(0, i);
			
			int cnt = 1; // 문자 출현 횟수
			int start = i; // substring 시작 인덱스
			while(start < len) {
				if(s.substring(start, len).startsWith(delim)) {
					cnt++;
				}
				else { 
					if(cnt == 1) sb.append(delim);
					else sb.append(delim).append(cnt);
					cnt = 1;
					delim = start + i < len ? s.substring(start, start + i) : s.substring(start, len);
				}
				start += i;
			}
			if(cnt == 1) sb.append(delim);
			else sb.append(delim).append(cnt);
			ans = Math.min(ans, sb.toString().length());
		}
		return ans;
	}

}
