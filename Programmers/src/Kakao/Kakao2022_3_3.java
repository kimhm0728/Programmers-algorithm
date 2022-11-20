package Kakao;

import java.util.*;

public class Kakao2022_3_3 {
	static int INF = 20000001;
	static int min = INF;
	static boolean[] visit;
	static ArrayList<int[]> list[];
	static int A, B;
	static int aCost, bCost;

	public static void main(String[] args) {
		int[][] fares = {{4, 1, 10}, 
				{3, 5, 24}, 
				{5, 6, 2}, 
				{3, 1, 41}, 
				{5, 1, 24}, 
				{4, 6, 50}, 
				{2, 4, 66}, 
				{2, 3, 22}, 
				{1, 6, 25}};

		System.out.println(solution(6,4,5,6, fares));
	}

	static int solution(int n, int s, int a, int b, int[][] fares) {
		visit = new boolean[n + 1];
		list = new ArrayList[n + 1];

		A = a;
		B = b;

		for(int i=1;i<=n;i++)
			list[i] = new ArrayList<>();

		for(int i=0;i<fares.length;i++) {
			int start = fares[i][0];
			int end = fares[i][1];
			int cost = fares[i][2];

			list[start].add(new int[] {end, cost});
			list[end].add(new int[] {start, cost});
		}

		visit[s] = true;
		DFS('t', 0, s);

		return min;
	}

	static void DFS(char type, int cost, int now) {
		if(type == 'a' && now == A) {
			aCost = Math.min(aCost, cost);
			return;
		}
		if(type == 'b' && now == B) {
			bCost = Math.min(bCost, cost);
			return;
		}
		if(type == 't') {
			aCost = bCost = INF;
			DFS('a', 0, now);
			DFS('b', 0, now);
			min = Math.min(min, cost + aCost + bCost);
		}

		for(int arr[] : list[now]) {
			int next = arr[0];
			int c = arr[1];

			if(!visit[next]) {
				visit[next] = true;
				DFS(type, cost + c, next);
				visit[next] = false;
			}
		}

	}
}
