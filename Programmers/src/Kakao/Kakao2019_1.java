package Kakao;

import java.util.Arrays;

public class Kakao2019_1 {

	public static void main(String[] args) {
		int[] result = solution(5, new int[] {2, 1, 2, 6, 2, 4, 3, 3});
		
		for(int i : result)
			System.out.print(i + " ");
	}
	
    static int[] solution(int N, int[] stages) {
        double[][] fail = new double[N][2]; // 스테이지 번호, 실패율
        int[] users = new int[N]; // 클리어하지 못한 사용자 수
        
        for(int i=0;i<N;i++)
            fail[i][0] = i;
        
        for(int i=0;i<stages.length;i++) {
            int st = stages[i] - 1;
            
            if(st != N)
                users[st]++;
            
            // 스테이지에 도전한 사용자
            for(int j=0;j<=st;j++)
                if(j != N)
                    fail[j][1]++;
        }
        
        // 실패율 계산
        for(int i=0;i<N;i++) {
            if(fail[i][1] == 0)
                continue;
            fail[i][1] = users[i] / fail[i][1];
        }
        
        Arrays.sort(fail, (o1, o2) -> 
        Double.compare(o1[1], o2[1]) == 0 ? 
        		Double.compare(o1[0], o2[0]) : Double.compare(o2[1], o1[1]));
        
        int[] ans = new int[N];
        
        for(int i=0;i<N;i++)
            ans[i] = (int)(fail[i][0] + 1);
        
        return ans;
    }

}
