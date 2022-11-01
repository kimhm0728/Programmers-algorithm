package CodingTestKit;

public class Graph2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public static int solution(int n, int[][] results) {
        int[] rank = new int[n + 1];
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<results.length;j++) {
                int a = results[i][0];
                int b = results[i][1];
                
                rank[a] = rank[b] == n - 1 ? n - 1 : rank[a] + 1;
                rank[b] = rank[a] == n - 1 ? n - 1 : rank[b] + 1;
            }
            for(int k=1;k<rank.length;k++) 
                rank[k] = rank[k] == n - 1 ? rank[k] : 0;
        }
        
        int cnt = 0;
        for(int i=1;i<rank.length;i++)
            if(rank[i] == n - 1) cnt++;
        
        return cnt;
    }

}
