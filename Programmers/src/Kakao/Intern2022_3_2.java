package Kakao;

public class Intern2022_3_2 {
    static int[][] dp;
	static int maxAlp, maxCop;

	public static void main(String[] args) {
		System.out.println(solution(10, 10, new int[][] {{10,15,2,1,2},{20,20,3,3,4}}));
	}

	static int solution(int alp, int cop, int[][] problems) {
		maxAlp = alp;
        maxCop = cop;
        
        for(int i=0;i<problems.length;i++) {
			if(maxAlp < problems[i][0])
				maxAlp = problems[i][0];
			if(maxCop < problems[i][1])
				maxCop = problems[i][1];
		}

		dp = new int[maxAlp + 1][maxCop + 1];
		for(int i=alp;i<=maxAlp;i++)
			for(int j=cop;j<=maxCop;j++)
				dp[i][j] = Integer.MAX_VALUE;

		dp[alp][cop] = 0;
		dp(alp, cop, problems);

		return dp[maxAlp][maxCop];
	}

	static void dp(int alp, int cop, int[][] problems) {
		for(int i=alp;i<=maxAlp;i++) {
			for(int j=cop;j<=maxCop;j++) {
				// 알고리즘 공부
				if(i + 1 <= maxAlp)
					dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);

				// 코딩 공부
				if(j + 1 <= maxCop)
					dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

				// 문제 풀기
				for(int k=0;k<problems.length;k++) {
					if(problems[k][0] <= i && problems[k][1] <= j) { // 풀 수 있는 문제라면
						int increaseAlp = problems[k][2];
						int increaseCop = problems[k][3];
						int hour = problems[k][4];
						
						// 최대 범위를 넘은 경우 최대범위로 조정
						if(i + increaseAlp > maxAlp) 
							increaseAlp = maxAlp - i;
						if(j + increaseCop > maxCop)
							increaseCop = maxCop - j;

						dp[i + increaseAlp][j + increaseCop] = Math.min(dp[i + increaseAlp][j + increaseCop], dp[i][j] + hour);
					}
				}
			}
		}
	}
}
