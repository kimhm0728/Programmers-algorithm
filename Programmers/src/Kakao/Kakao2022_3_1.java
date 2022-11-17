package Kakao;

import java.util.*;

public class Kakao2022_3_1 {
	static boolean visit[][][];
	static ArrayList<Integer> list[];
	static int max = 0;
	
	public static void main(String[] args) {
		int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
		int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
		System.out.println(solution(info, edges));
	}

	static int solution(int[] info, int[][] edges) {
		int n = info.length;
		list = new ArrayList[n];
		visit = new boolean[n][n + 1][n + 1]; // ÀÎµ¦½º, ¾ç, ´Á´ë

		for(int i=0;i<n;i++)
			list[i] = new ArrayList<>();

		for(int i=0;i<edges.length;i++) {
			int parent = edges[i][0];
			int child = edges[i][1];

			list[parent].add(child);
            list[child].add(parent);
		}

		DFS(info, 0, 0, 0);

		return max;
	}

	static void DFS(int[] info, int start, int sheep, int wolf) {
        if(info[start] == 0)
            sheep++;
        else 
            wolf++;
        
        if(wolf >= sheep)
            return;
		
        max = Math.max(sheep, max);
        
        for(int next : list[start]) {
            int temp = info[start];
            if(!visit[next][sheep][wolf]) {
                visit[next][sheep][wolf] = true;
                info[start] = -1; 
                
                DFS(info, next, sheep, wolf);
                info[start] = temp;
                visit[next][sheep][wolf] = false;

            }
        }
        
	}

}
