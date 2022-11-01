package CodingTestKit;

import java.util.Queue;
import java.util.LinkedList;

public class DFS_BFS3 {
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int[][] result;

	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}}));
	}
	
    public static int solution(int[][] maps) {
        return BFS(maps, maps.length, maps[0].length);
    }
    
    static int BFS(int[][] arr, int x, int y) {
    	result = new int[x][y];
    	Queue<int[]> q = new LinkedList<>();
    	
    	q.offer(new int[] {0, 0});
    	result[0][0] = 1;
    	
    	while(!q.isEmpty()) {
    		int[] now = q.poll();
    		int nowX = now[0];
    		int nowY = now[1];
    		
    		if(nowX == x - 1 && nowY == y - 1)
    			return result[nowX][nowY];
    		
    		for(int i=0;i<4;i++) {
    			int nextX = nowX + dx[i];
    			int nextY = nowY + dy[i];
    			
    			if(nextX < x && nextX >= 0 && nextY < y && nextY >= 0 &&
    					arr[nextX][nextY] == 1 && result[nextX][nextY] == 0) {
    				result[nextX][nextY] = result[nowX][nowY] + 1;
    				q.offer(new int[] {nextX, nextY});
    			}
    		}
    	}
    	
    	return -1;
    }

}
