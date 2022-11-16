package KakaoIntern;

import java.util.Queue;
import java.util.LinkedList;

public class Intern2020_3_1 {
	static int cost[][][];
	// 시계 방향
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int n;
	
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {
			{0, 0, 0, 0, 0, 0, 0, 0}, 
			{1, 0, 1, 1, 1, 1, 1, 0}, 
			{1, 0, 0, 1, 0, 0, 0, 0}, 
			{1, 1, 0, 0, 0, 1, 1, 1}, 
			{1, 1, 1, 1, 0, 0, 0, 0}, 
			{1, 1, 1, 1, 1, 1, 1, 0}, 
			{1, 1, 1, 1, 1, 1, 1, 0}, 
			{1, 1, 1, 1, 1, 1, 1, 0}}));
	}
	
    static int solution(int[][] board) {
    	n = board.length;
    	cost = new int[4][n][n];
    	
    	for(int i=0;i<4;i++)
    		for(int j=0;j<n;j++)
    			for(int k=0;k<n;k++)
    				cost[i][j][k] = Integer.MAX_VALUE;
    	
    	BFS(board);
    	
    	int ans = Integer.MAX_VALUE;
    	for(int i=0;i<4;i++)
    		ans = Math.min(ans, cost[i][n - 1][n - 1]);
    	
    	return ans;
    }
    
	static void BFS(int[][] board) {
		Queue<int[]> q = new LinkedList<>();
		
		// 방향, 행, 열
		if(board[0][1] == 0) {
			cost[1][0][1] = 100;
			q.offer(new int[] {1, 0, 1});
		}
		if(board[1][0] == 0) {
			cost[2][1][0] = 100;
			q.offer(new int[] {2, 1, 0});
		}
		
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int dir = arr[0];
			int x = arr[1];
			int y = arr[2];
			
			if(x == n - 1 && y == n - 1) 
				continue;
			
			for(int nextDir=0;nextDir<4;nextDir++) {
				int nextX = x + dx[nextDir];
				int nextY = y + dy[nextDir];
				
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && board[nextX][nextY] == 0) {
					if(dir != nextDir) { 
						if(dir + nextDir == 2 || dir + nextDir == 4) // 역방향
							continue;
						if(cost[nextDir][nextX][nextY] < cost[dir][x][y] + 600)
							continue;
						q.add(new int[] {nextDir, nextX, nextY});
						cost[nextDir][nextX][nextY] = cost[dir][x][y] + 600;
					}
					else {
						if(cost[nextDir][nextX][nextY] < cost[dir][x][y] + 100)
							continue;
						q.add(new int[] {nextDir, nextX, nextY});
						cost[nextDir][nextX][nextY] = cost[dir][x][y] + 100;
					}
				}
			}
		}
	}
	
}
