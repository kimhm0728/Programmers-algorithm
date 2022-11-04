package Kakao;

import java.util.Stack;

public class Intern2019_1 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int b[][] = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int m[] = {1,5,3,5,1,2,1,4};
		System.out.println(solution(b, m)); // 4
	}
	
    static int solution(int[][] board, int[] moves) {
        int n = board.length;
    	Stack<Integer>[] doll = new Stack[n];
    	
    	for(int i=0;i<n;i++)
    		doll[i] = new Stack<>();
    	
        for(int i=n-1;i>=0;i--) {
        	for(int j=0;j<n;j++) {
        		if(board[i][j] != 0) {
        			doll[j].push(board[i][j]);
        		}
        	}
        }
        
        int cnt = 0;
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0;i<moves.length;i++) {
        	int m = moves[i] - 1;
        	if(doll[m].size() != 0) {
        		int d = doll[m].pop();
        		if(!stack.isEmpty() && stack.peek() == d) {
        			stack.pop();
        			cnt += 2;
        		}
        		else 
        			stack.push(d);
        	}
        }

        return cnt;
    }
}
