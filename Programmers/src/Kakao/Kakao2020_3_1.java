package Kakao;

public class Kakao2020_3_1 {
	static int key_len;
	static int lock_len;
	static int board_len;
	
	public static void main(String[] args) {
		int[][] key = {{0, 0, 0},
				{1, 0, 0},
				{0, 1, 1}};
		
		int[][] lock = {{1, 1, 1},
				{1, 1, 0},
				{1, 0, 1}};
		
		System.out.println(solution(key, lock));
	}
    
	static boolean solution(int[][] key, int[][] lock) {
        key_len = key.length;
        lock_len = lock.length;
        board_len = lock_len + 2 * (key_len - 1);
		
        // 상하좌우 key_len - 1 만큼 padding
        int[][] board = new int[board_len][board_len];
        
        // padding 한 배열의 중앙에 lock 대입
        for(int i=0;i<lock_len;i++) {
        	for(int j=0;j<lock_len;j++)
        		board[i + key_len - 1][j + key_len - 1] = lock[i][j];
        }
        
        for(int i=0;i<board_len - (key_len - 1);i++) {
        	for(int j=0;j<board_len - (key_len - 1);j++) {
    			int[][] new_key = key;
 
    			// 회전 안한 상태로 한 번 체크, 3번 회전해서 체크
        		for(int k=0;k<4;k++) {
        			if(k != 0)
        				new_key = rotate(new_key);
        			
        			int[][] new_board = boardSum(board, new_key, i, j);
        			if(check(new_board))
        				return true;
        		}
        	}
        }
        
        return false;
    }
	
	// board에 key를 합치기
	static int[][] boardSum(int[][] board, int[][] key, int x, int y) {
		int[][] new_board = new int[board_len][board_len];
		
		for(int i=0;i<board_len;i++)
			new_board[i] = board[i].clone();
		
		for(int i=0;i<key_len;i++) {
			for(int j=0;j<key_len;j++)
				new_board[x + i][y + j] += key[i][j];
		}
		
		return new_board;
	}
	
	// lock 위치가 모두 1인지 체크
	static boolean check(int[][] board) {
		for(int i=0;i<lock_len;i++) {
			for(int j=0;j<lock_len;j++)
				if(board[i + key_len - 1][j + key_len - 1] != 1)
					return false;
		}
		
		return true;
	}
	
	// 시계 방향으로 90도 회전
	static int[][] rotate(int[][] key) {
		int[][] newKey = new int[key_len][key_len];
		
		for(int i=0;i<key_len;i++) {
			int[] temp = key[i];
			for(int j=0;j<key_len;j++)
				newKey[j][key_len - 1 - i] = temp[j];
		}
		
		return newKey;
	}
	
}
