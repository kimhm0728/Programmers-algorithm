package programmers;

import java.util.*;

public class Lighthouse {

	public static void main(String[] args) {
		System.out.println(solution(8, new int[][] {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}}));
	}

	static ArrayList<Integer>[] list;
	static ArrayList<Integer> node;
	static boolean[] on;
	static boolean[] visit;
	static boolean[] leaf;
	static int answer = 1000001;

	static public int solution(int n, int[][] lighthouse) {
		list = new ArrayList[n];
		node = new ArrayList<>();
		on = new boolean[n];
		leaf = new boolean[n];
		visit = new boolean[n];

		for(int i=0;i<n;i++)
			list[i] = new ArrayList<>();

		for(int i=0;i<n-1;i++) {
			int start = lighthouse[i][0] - 1;
			int end = lighthouse[i][1] - 1;

			list[start].add(end);
			list[end].add(start);
		}

		int cnt = 0;
		for(int i=0;i<n;i++) {
			if(list[i].size() == 1) {
				leaf[i] = true;
				for(int o : list[i]) {
					cnt += on[o] ? 0 : 1;
					visit[o] = true;
					on[o] = true;
				}
			}
		}

		for(int i=0;i<n;i++) 
			if(!leaf[i] && !on[i])
				node.add(i);

		int size = node.size();
		if(size == 0)
			return cnt;
		
		DFS(0, 0, size);
		return answer + cnt;
	}

	static void DFS(int idx, int cnt, int n) {
		if(idx == n) {
			for(int i=0;i<n;i++) {
				int nd = node.get(i);
				for(int o : list[nd])
					if(!visit[o] && !visit[nd])
						return;
			}
			answer = Math.min(cnt, answer);
			return;
		}

		int nd = node.get(idx);
		visit[nd] = true;
		DFS(idx + 1, cnt + 1, n);
		visit[nd] = false;
		DFS(idx + 1, cnt, n);
	}

}
