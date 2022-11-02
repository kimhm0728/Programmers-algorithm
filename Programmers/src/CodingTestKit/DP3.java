package CodingTestKit;

public class DP3 {
	static int d[][];
	
	public static void main(String[] args) {
		System.out.println(solution(4, 3, new int[][] {{2, 2}}));
	}
	
    static int solution(int m, int n, int[][] puddles) {
        d = new int[n + 1][m + 1];
        d[1][1] = 1;
        
        for(int i=0;i<puddles.length;i++) 
        	d[puddles[i][1]][puddles[i][0]] = -1;
        
        for(int i=1;i<=n;i++) {
        	for(int j=1;j<=m;j++) {
        		if(d[i][j] == -1)
        			continue; 
        		if(j != m && d[i][j + 1] != -1)
        			d[i][j + 1] += d[i][j] % 1000000007;
        		if(i != n && d[i + 1][j] != -1)
        			d[i + 1][j] += d[i][j] % 1000000007;
        	}
        }
        
        return d[n][m] % 1000000007;
    }

}
