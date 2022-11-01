package CodingTestKit;

import java.util.ArrayList;

public class DFS_BFS2 {
	static boolean visit[];
	static ArrayList<Integer> list[];
	static int cnt = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(3, new int[][] {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
	}

	public static int solution(int n, int[][] computers) {
		visit = new boolean[n];
		list = new ArrayList[n];

		for(int i=0;i<n;i++) {
			list[i] = new ArrayList<>();
			for(int j=0;j<n;j++) {
				if(i != j && computers[i][j] == 1)
					list[i].add(j);
			}
		}

		for(int i=0;i<n;i++) {
			if(!visit[i]) {
				DFS(i);
				cnt++;
			}
		}
		return cnt;

	}
	
	static void DFS(int start) {
		visit[start] = true;
		
		for(int i : list[start]) {
			if(!visit[i]) {
				visit[i] = true;
				DFS(i);
			}
		}
	}
	

}
