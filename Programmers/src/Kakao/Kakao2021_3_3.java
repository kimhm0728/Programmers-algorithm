package Kakao;

import java.util.*;

public class Kakao2021_3_3 {
	static ArrayList<Point> list[] = new ArrayList[7];
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		int[][] board = {{1, 0, 0, 3},
				{2, 0, 0, 0},
				{0, 0, 0, 2},
				{3, 0, 1, 0}};
		
		System.out.println(solution(board, 1, 0));
	}
	
    static int solution(int[][] board, int r, int c) {
    	for(int i=1;i<7;i++)
    		list[i] = new ArrayList<>();
    	
    	int n = board.length;
    	int m = board[0].length;
    	
    	// hashmap에 담기. key는 카드번호, value는 카드 위치 두개.
    	for(int i=0;i<n;i++)
    		for(int j=0;j<m;j++)
    			if(board[i][j] != 0)
    				list[board[i][j]].add(new Point(i, j, 0));
    	
    	DFS(0);

    	return min;
    }
    
	static void DFS(int depth) {
		
		// 카드갯수만큼 배열에 담았으면 bfs로 최솟값 찾기
		// if(depth == 카드갯수) Math.min(bfs, min)
		
		for(int i=1;i<7;i++) {
			//int dis = BFS();
			//int reverse = BFS();
			
			// 배열에는 위치 담기
			// 0에는 시작, 1에는 끝
			// 0에는 끝, 1에는 시작 이런 식으로 dfs 두번 진행. visit 추가.
		}
		
	}
	
	static void BFS() {
		// 큐로 시작점 넣기. 시작점부터 카드 하나하나 
		// ctrl으로 (최대 2까지 방향), ctrl없이 하나만 가는 것 둘다 q에 넣기
	}
	
	static class Point {
		int row, col, cnt;
		
		Point(int row, int col, int cnt) {
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
	}

}
