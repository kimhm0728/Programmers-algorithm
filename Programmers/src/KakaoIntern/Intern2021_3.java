package KakaoIntern;

import java.util.StringTokenizer;
import java.util.Stack;

public class Intern2021_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(13, 0, new String[] {"D 12", "C"}));
	}

	static String solution(int n, int k, String[] cmd) {
		StringTokenizer st;
		Stack<int[]> stack = new Stack<>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("O".repeat(n));
		
		int[] pre = new int[n];
		int[] next = new int[n];
		
		for(int i=0;i<n;i++) {
			pre[i] = i - 1;
			next[i] = i + 1;
		}
		
		next[n - 1] = -1;

		for(int i=0;i<cmd.length;i++) {
			st = new StringTokenizer(cmd[i]);
			int tmp;
			
			switch(st.nextToken()) {
			case "U":
				tmp = Integer.parseInt(st.nextToken());
				while(tmp-- > 0) 
					k = pre[k];
				break;
			case "D":
				tmp = Integer.parseInt(st.nextToken());
				while(tmp-- > 0) 
					k = next[k];
				break;
			case "C":
				stack.push(new int[] {pre[k], k, next[k]});
				if(pre[k] != -1) 
					next[pre[k]] = next[k];
				if(next[k] != -1) 
					pre[next[k]] = pre[k];
				
				sb.setCharAt(k, 'X');

				if(next[k] == -1) 
					k = pre[k];
				else 
					k = next[k];
				break;
			case "Z":
				int[] arr = stack.pop();
				if(arr[0] != -1)
					next[arr[0]] = arr[1];
				if(arr[2] != -1)
					pre[arr[2]] = arr[1];
				
				sb.setCharAt(arr[1], 'O');
				break;
			}
		}

		return sb.toString();
	}

}
