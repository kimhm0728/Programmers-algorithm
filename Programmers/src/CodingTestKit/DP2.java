package CodingTestKit;

public class DP2 {
    static int d[][];
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new int[][] {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}));
	}
	
    static int solution(int[][] triangle) {
    	int h = triangle.length;
        d = new int[h][h];
        
        // 삼각형 가장 마지막 행을 복사
        for(int i=0;i<triangle[h - 1].length;i++)
        	d[h - 1][i] = triangle[h - 1][i];

        return dp(triangle, triangle.length - 2);
    }
    
    static int dp(int[][] arr, int h) {
    	for(int i=0;i<arr[h].length;i++) 
        	d[h][i] = Math.max(d[h + 1][i], d[h + 1][i + 1]) + arr[h][i];

        if(h == 0) 
        	return d[0][0];
        return dp(arr, h - 1);
    }

}
