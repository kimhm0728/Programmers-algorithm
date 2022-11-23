package Kakao;

import java.util.*;

public class Kakao2020_3_4 {
	// 상하좌우 이동
	static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	// 가로 -> 세로 회전 (↖ ↙ ↗ ↘ 순서)
	static int[][] hRocate = {{-1, 0}, {0, 0}, {-1, 1}, {0, 1}};
	static int[][] hCheck = {{-1, 1}, {1, 1}, {-1, 0}, {1, 0}};
	// 세로 -> 가로 회전 (↖ ↙ ↗ ↘ 순서)
	static int[][] vRocate = {{0, -1}, {1, -1}, {0, 0}, {1, 0}};
	static int[][] vCheck = {{1, -1}, {0, -1}, {1, 1}, {0, 1}};

	static int[][] newBoard;
	static int min = Integer.MAX_VALUE;
	static boolean[][][] visit;
	static int n;

	public static void main(String[] args) {
		int board[][] = {{0, 0, 0, 1, 1},
				{0, 0, 0, 1, 0},
				{0, 1, 0, 1, 1},
				{1, 1, 0, 0, 1},
				{0, 0, 0, 0, 0}};

		System.out.println(solution(board));
	}

	static class Robot {
		int x, y, dir, sec;

		Robot(int x, int y, int dir, int sec) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.sec = sec;
		}
	}

	static int solution(int[][] board) {
		newBoard = board;
		n = board.length;

		// 0: 가로, 1: 세로
		visit = new boolean[2][n][n];

		BFS();
		return min;
	}

	static void BFS() {
		Queue<Robot> q = new LinkedList<>();

		q.offer(new Robot(0, 0, 0, 0));
		visit[0][0][0] = true;

		while(!q.isEmpty()) {
			Robot now = q.poll();

			if((now.dir == 0 && now.x == n - 1 && now.y + 1 == n - 1) || 
					(now.dir == 1 && now.x + 1 == n - 1 && now.y == n - 1)) {
				min = Math.min(min, now.sec);
				continue;
			}

			// 상하좌우 이동
			for(int i=0;i<4;i++) {
				int nextX = now.x + move[i][0];
				int nextY = now.y + move[i][1];

				if(check(nextX, nextY, now.dir)) {
					visit[now.dir][nextX][nextY] = true;
					q.offer(new Robot(nextX, nextY, now.dir, now.sec + 1));
				}
			}

			if(now.dir == 0) { // 가로 -> 세로 회전
				for(int i=0;i<4;i++) {
					int nextX = now.x + hRocate[i][0];
					int nextY = now.y + hRocate[i][1];
					int nextDir = 1;

					if(check(nextX, nextY, nextDir) && newBoard[now.x + hCheck[i][0]][now.y + hCheck[i][1]] == 0) {
						visit[nextDir][nextX][nextY] = true;
						q.offer(new Robot(nextX, nextY, nextDir, now.sec + 1));
					}
				}
			}
			else { // 세로 -> 가로 회전
				for(int i=0;i<4;i++) {
					int nextX = now.x + vRocate[i][0];
					int nextY = now.y + vRocate[i][1];
					int nextDir = 0;

					if(check(nextX, nextY, nextDir) && newBoard[now.x + vCheck[i][0]][now.y + vCheck[i][1]] == 0) {
						visit[nextDir][nextX][nextY] = true;
						q.offer(new Robot(nextX, nextY, nextDir, now.sec + 1));
					}
				}
			}
		}
	}

	static boolean check(int x, int y, int dir) {
		// 이미 방문한 경우, 배열 범위 벗어난 경우, 벽인 경우
		if(x < 0 || x >= n || y < 0 || y >= n || visit[dir][x][y] || newBoard[x][y] == 1)
			return false;

		if(dir == 0 && (y + 1 >= n || newBoard[x][y + 1] == 1)) 
			return false;

		if(dir == 1 && (x + 1 >= n || newBoard[x + 1][y] == 1)) 
			return false;

		return true;
	}

}
