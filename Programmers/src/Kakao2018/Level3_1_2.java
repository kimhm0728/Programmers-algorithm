package Kakao2018;

import java.util.*;

public class Level3_1_2 {
	// 2018 KAKAO BLIND RECRUITMENT 1차 Level 3 두 번째 문제 추석 트래픽

	public static void main(String[] args) {
		System.out.println(solution(new String[] {"2016-09-15 01:00:04.001 2.0s",
				"2016-09-15 01:00:07.000 2s"}));
	}
	
	static int solution(String[] lines) {
        int n = lines.length;
        int[][] time = new int[n][2]; // 시작 시간, 끝 시간
        
        for(int i=0;i<n;i++) {
            int end = strToInt(lines[i].substring(11, 23));
            int T = (int)(Double.parseDouble(lines[i].substring(24, lines[i].length() - 1)) * 1000);
            time[i][0] = end - T;
            time[i][1] = end;
        }
        
        int answer = 0;
        return answer;
    }
    
    static int strToInt(String str) {
        StringTokenizer st = new StringTokenizer(str, ":");
        int time = 0;
        time += Integer.parseInt(st.nextToken()) * 3600;
        time += Integer.parseInt(st.nextToken()) * 60;
        time *= 1000;
        time += (int)(Double.parseDouble(st.nextToken()) * 1000);
        System.out.println(time);
        return time;
    }

}
