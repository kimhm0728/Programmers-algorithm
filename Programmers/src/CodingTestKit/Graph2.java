package CodingTestKit;

public class Graph2 {
	public static void main(String[] args) {
		System.out.println(solution(5, new int[][] {{4,3}, {4,2}, {3,2}, {1,2}, {2,5}}));
	}
	
    public static int solution(int n, int[][] results) {
        int[][] arr = new int[n+1][n+1];
        
        for(int i=0;i<results.length;i++) {
            int a = results[i][0];
            int b = results[i][1];
            arr[a][b] = 1;
            arr[b][a] = -1;
        }
        
        for(int k=1;k<n+1;k++) {
            for(int i=1;i<n+1;i++) 
                for(int j=1;j<n+1;j++)
                    if(arr[i][k] == 1 && arr[k][j] == 1) {
                        arr[i][j] = 1;
                        arr[j][i] = -1;
                    }
        }
        
        int cnt = 0;
        for(int i=1;i<n+1;i++) {
            int temp = 0;
            for(int j=1;j<n+1;j++)
                if(arr[i][j] != 0)
                    temp++;
            if(temp == n - 1)
                cnt++;
        }
        
        return cnt;
    }


}
