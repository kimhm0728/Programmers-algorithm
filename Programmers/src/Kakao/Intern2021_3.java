package Kakao;

import java.util.StringTokenizer;
import java.util.Stack;
import java.util.ArrayList;

public class Intern2021_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(8, 2, new String[] {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}));
	}

	static String solution(int n, int k, String[] cmd) {
		StringTokenizer st;
		Stack<int[]> stack = new Stack<>();
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i=0;i<n;i++)
			list.add(i);

		for(int i=0;i<cmd.length;i++) {
			st = new StringTokenizer(cmd[i]);
			
			switch(st.nextToken()) {
			case "U":
				k -= Integer.parseInt(st.nextToken());
				break;
			case "D":
				k += Integer.parseInt(st.nextToken());
				break;
			case "C":
				stack.push(new int[] {k, list.get(k)});
				list.remove(k);
				if(k == list.size()) 
					k--;
				break;
			case "Z":
				int[] arr = stack.pop();
				if(arr[0] <= k)
					k++;
				list.add(arr[0], arr[1]);
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append("X".repeat(n));
		for(int i=0;i<list.size();i++) 
			sb.setCharAt(list.get(i), 'O');

		return sb.toString();
	}

}
