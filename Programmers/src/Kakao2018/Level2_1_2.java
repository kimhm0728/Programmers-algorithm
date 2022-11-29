package Kakao2018;

public class Level2_1_2 {
	// 2018 KAKAO BLIND RECRUITMENT 1차 Level 2 두 번째 문제 프렌즈4블록

	static char[][] new_board;

	public static void main(String[] args) {
		String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		System.out.println(solution(4, 5, board));
	}

	static int solution(int m, int n, String[] board) {
		int ans = 0;
		new_board = new char[m][n];

		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++) 
				new_board[i][j] = board[i].charAt(j);

		while(true) {
			boolean[][] delete = new boolean[m][n];

			// 2x2 블록 찾기
			for(int i=0;i<m-1;i++) { // 세로(열)
				for(int j=0;j<n-1;j++) { // 가로(행)
					char c = new_board[i][j];
					if(c == '*')
						continue;
					if(new_board[i][j + 1] == c && new_board[i + 1][j] == c && new_board[i + 1][j + 1] == c) {
						delete[i][j] = true;
						delete[i][j + 1] = true;
						delete[i + 1][j] = true;
						delete[i + 1][j + 1] = true;
					}
				}
			}

			int del = 0;
			for(int i=0;i<m;i++) {
				for(int j=0;j<n;j++)
					if(delete[i][j]) {
						del++;
						deleteBlock(i, j);
					}
			}

			// 지워진 블록이 없다면 while문 빠져나오기
			if(del == 0)
				break; 
			ans += del;
		}

		return ans;
	}

	static void deleteBlock(int x, int y) {
		if(x == 0) { // 가장 윗 행이면 바로 블록 지우기 (지워진 블록은 * 표시)
			new_board[x][y] = '*';
		}
		else {
			// 위에 있는 행을 아래로 한칸씩 내리고 가장 윗 행에 있는 블록 지우기
			for(int i=x-1;i>=0;i--) 
				new_board[i + 1][y] = new_board[i][y];
			new_board[0][y] = '*';
		}
	}

}
