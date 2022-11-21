package Kakao;

import java.util.*;

public class Kakao2021_3_1 {

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

		System.out.println(solution(6, 4, 6, 2, fares));
	}

	static int solution(int n, int s, int a, int b, int[][] fares) {
		int[][] arr = new int[n + 1][n + 1];

		for(int i=1;i<=n;i++) {
			Arrays.fill(arr[i], 20000001);
			arr[i][i] = 0;
		}
		
		for(int i=0;i<fares.length;i++) {
			int start = fares[i][0];
			int end = fares[i][1];
			int cost = fares[i][2];

			arr[start][end] = cost;
			arr[end][start] = cost;
		}

		for(int k=1;k<=n;k++)
			for(int i=1;i<=n;i++)
				for(int j=1;j<=n;j++)
					if(arr[i][j] > arr[i][k] + arr[k][j])
						arr[i][j] = arr[i][k] + arr[k][j];

		int min = 20000001;

		// s에서 i까지 합승 후 각자 a와 b로 가는 경우
		for(int i=1;i<=n;i++)
			min = Math.min(min, arr[s][i] + arr[i][a] + arr[i][b]);

		return min;
	}

}
