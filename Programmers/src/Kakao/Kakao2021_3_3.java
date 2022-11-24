package Kakao;

import java.util.*;

public class Kakao2021_3_3 {
	static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static HashMap<Integer, int[][]> map = new HashMap<>();
	static int min = Integer.MAX_VALUE;
	static int n = 0;
	static int[][] card;
	static boolean[] visit;
	static int[][] bfs;
	static int R, C;
	static int board_len;
	
	public static void main(String[] args) {
		int[][] board = {{1, 5, 0, 2}, {6, 4, 3, 0}, {0, 2, 1, 5}, {3, 0, 6, 4}};
		
		System.out.println(solution(board, 0, 0));
	}
	
    static int solution(int[][] board, int r, int c) {
    	R = r; C = c;
    	board_len = board.length;
    	
    	// key는 카드 번호, value는 카드 위치 두개.
    	for(int i=0;i<board.length;i++)
    		for(int j=0;j<board[0].length;j++) 
    			if(board[i][j] != 0) {
    				if(!map.containsKey(board[i][j])) {
    					int[][] temp = new int[2][2];
    					temp[0][0] = i; temp[0][1] = j;
    					map.put(board[i][j], temp);
    					n++;
    				}
    				else {
    					int[][] temp = map.get(board[i][j]);
    					temp[1][0] = i; temp[1][1] = j;
    				}
    			}
    	
    	// 카드가 3개있을 때 카드번호가 1,2,3 인지 1,4,5처럼 순차적이지 않을수있는지. 일단 전자로 구현.
    	card = new int[n * 2][2];
    	visit = new boolean[n + 1];
    	
    	DFS(0);

    	return min;
    }
    
	static void DFS(int depth) {
		// 카드갯수만큼 배열에 담았으면 BFS로 최솟값 찾기
		if(depth == n) { 
			min = Math.min(BFS(), min);
			return;
		}
		
		for(int i=1;i<=n;i++) {
			if(!visit[i] && map.containsKey(i)) {
				visit[i] = true;
				int pos[][] = map.get(i);
				int idx = depth * 2;
				card[idx] = pos[0]; card[idx + 1] = pos[1];
				DFS(depth + 1);
				
				// 같은 번호의 카드 뽑는 순서 반대로
				card[idx] = pos[1]; card[idx + 1] = pos[0];
				DFS(depth + 1);
				visit[i] = false;
			}
		}
		
	}
	
	static int BFS() {
		Queue<Point> q = new LinkedList<>();
		
		int cnt = 0;
		int startX = R;
		int startY = C;
		
		// start 부터 card의 원소까지
		for(int i=0;i<card.length;i++) {
			bfs = new int[board_len][board_len];
			q.clear();
			q.offer(new Point(startX, startY, 0));
			
			while(!q.isEmpty()) {
				Point now = q.poll();
				
				if(card[i][0] == now.x && card[i][1] == now.y) {
					startX = now.x;
					startY = now.y;
					cnt += now.cnt + 1;
					break;
				}
				
				for(int j=0;j<4;j++) {
					int nextX = now.x + move[j][0];
					int nextY = now.y + move[j][1];
					
					// 방향키만 누르는 경우
					if(check(nextX, nextY) && bfs[nextX][nextY] == 0) {
						bfs[nextX][nextY] = now.cnt + 1;
						q.offer(new Point(nextX, nextY, now.cnt + 1));
					}
					
					// 방향키랑 ctrl 키를 같이 누르는 경우
					for(int k=1;k<=board_len - 2;k++) {
						int newX = nextX, newY = nextY;
						
						if(move[j][0] != 0) {
							newX += move[j][0] < 0 ? -k : k;
						}
						else
							newY += move[j][1] < 0 ? -k : k;
						
						if(check(newX, newY) && bfs[newX][newY] == 0) {
							bfs[newX][newY] = now.cnt + 1;
							q.offer(new Point(newX, newY, now.cnt + 1));
						}
					}
				}
			}
			
		}
		
		return cnt;
	}
	
	static boolean check(int x, int y) {
		if(x < 0 || x >= board_len || y < 0 || y >= board_len)
			return false;
		
		return true;
	}
	
	static class Point {
		int x, y, cnt;
		
		Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

}
