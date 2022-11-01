package CodingTestKit;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Graph1 {
	static int[] visit;
	static ArrayList<Integer>[] list;
	static int max = -1;
	
	public static void main(String[] args) {
		System.out.println(solution(6, new int[][] {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
	}

	public static int solution(int n, int[][] edge) {
		visit = new int[n + 1];
		list = new ArrayList[n + 1];
		
		for(int i=1;i<=n;i++)
			list[i] = new ArrayList<>();
		
		for(int i=0;i<edge.length;i++) {
			int a = edge[i][0];
			int b = edge[i][1];
			
			list[a].add(b);
			list[b].add(a);
		}
		
		BFS(1);
		
		int cnt = 0;
		for(int i=1;i<visit.length;i++)
			if(max == visit[i])
				cnt++;
		
		return cnt;
	}
	
	static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visit[start] = 1;
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			
			for(int i : list[temp]) {
				if(visit[i] == 0) {
					visit[i] = visit[temp] + 1;
					max = Math.max(visit[i], max);
					q.offer(i);
				}
			}
		}
	}
}
