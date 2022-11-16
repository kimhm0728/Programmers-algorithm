package Kakao;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Kakao2022_1 {

	public static void main(String[] args) {
		int[] result = solution(new String[] {"muzi", "frodo", "apeach", "neo"},
				new String[] {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2);
		
		for(int i : result)
			System.out.println(i);
	}
	
    static int[] solution(String[] id_list, String[] report, int k) {
        StringTokenizer st;
        int n = id_list.length;
        boolean[][] report_yn = new boolean[n][n]; // 사용자별 신고 여부
        int[] report_cnt = new int[n]; // 신고 횟수
        int[] mail = new int[n]; // 메일 수
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i=0;i<n;i++)
            map.put(id_list[i], i); // 각 사용자 별로 인덱스 부여
        
        for(int i=0;i<report.length;i++) {
            st = new StringTokenizer(report[i]);
            // 사용자의 인덱스 찾기
            int userIdx = map.get(st.nextToken()); 
            int reportIdx = map.get(st.nextToken());
            
            if(!report_yn[userIdx][reportIdx]) { // 이미 같은 신고를 한 경우 무시
                report_yn[userIdx][reportIdx] = true;
                report_cnt[reportIdx]++;
            }
        }
        
        for(int i=0;i<n;i++) {
            if(report_cnt[i] >= k) {
                for(int j=0;j<n;j++) 
                    if(report_yn[j][i]) // j가 i를 신고했다면
                        mail[j]++;
            }
        }
        
        return mail;
    }

}
