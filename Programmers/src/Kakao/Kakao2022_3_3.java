package Kakao;

import java.util.*;

public class Kakao2022_3_3 {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int n, m;
	
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{1,1,1}, {1,1,1}, {1,1,1}},
				new int[] {1,0}, new int[] {1,2}));
	}

    static int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        
    	int answer = -1;
        return answer;
    }
    
    static void DFS(int[][] board, boolean isMove, boolean isWin, int[] aloc, int[] bloc, int a, int b) {
    	int[] target = isMove ? aloc : bloc;
    	boolean flag = false;
    	
    	for(int i=0;i<4;i++) {
    		int nextX = target[0] + dx[i];
    		int nextY = target[1] + dy[i];
    		
    		if(nextX < n && nextX >=0 && nextY < m && nextY >= 0 && board[nextX][nextY] == 1) {
    			board[nextX][nextY] = 0;
    			// DFS
    			board[nextX][nextY] = 1;
    		}
    	}
    	
    }
    
}
