package Kakao;

public class Kakao2022_3_2 {

	public static void main(String[] args) {
		int[][] board = {{5,5,5,5,5},
				{5,5,5,5,5},
				{5,5,5,5,5},
				{5,5,5,5,5}};
		
		int[][] skill = {{1,0,0,3,4,4},
				{1,2,0,2,3,2},
				{2,1,0,3,1,2},
				{1,0,1,3,3,1}};
		
		System.out.println(solution(board, skill));
	}
	
    static int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        
        int[][] sum = new int[n + 1][m + 1];
        
        for(int i=0;i<skill.length;i++) {
            int type = skill[i][0] == 1 ? -1 : 1;
            type *= skill[i][5];
            
            int x1 = skill[i][1];
            int y1 = skill[i][2];
            int x2 = skill[i][3];
            int y2 = skill[i][4];
            
            sum[x1][y1] += type;
            sum[x1][y2 + 1] -= type;
            sum[x2 + 1][y1] -= type;
            sum[x2 + 1][y2 + 1] += type;
        }
        
        // 위에서 아래로 누적합
        for(int i=1;i<n;i++)
            for(int j=0;j<m;j++)
                sum[i][j] += sum[i - 1][j];
        
        // 왼쪽에서 오른쪽 누적합
        for(int i=1;i<m;i++)
            for(int j=0;j<n;j++)
                sum[j][i] += sum[j][i - 1];
        
        int cnt = 0;
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if(board[i][j] + sum[i][j] >= 1) cnt++;
        
        return cnt;
    }
}
