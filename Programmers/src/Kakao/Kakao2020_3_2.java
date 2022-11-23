package Kakao;

import java.util.*;

public class Kakao2020_3_2 {
	static boolean[][] pillar;
	static boolean[][] beam;
	static int N;

	public static void main(String[] args) {
		int[][] build_frame = {{1,0,0,1},
				{1,1,1,1},
				{2,1,0,1},
				{2,2,1,1},
				{5,0,0,1},
				{5,1,0,1},
				{4,2,1,1},
				{3,2,1,1}};

		int[][] result = solution(5, build_frame);
		for(int[] arr : result) {
			for(int i : arr)
				System.out.print(i + " ");
			System.out.println();
		}

	}

	static int[][] solution(int n, int[][] build_frame) {
		N = n;
		pillar = new boolean[n + 3][n + 3];
		beam = new boolean[n + 3][n + 3];

		for(int i=0;i<build_frame.length;i++) {
			int x = build_frame[i][0] + 1;
			int y = build_frame[i][1] + 1;
			int a = build_frame[i][2]; // 0: 기둥, 1: 보
			int b = build_frame[i][3]; // 0: 삭제, 1: 설치

			if(a == 0 && b == 1) {
				if(canPillar(x, y)) 
					pillar[x][y] = true;
			}
			else if(a == 0 && b == 0) {
				pillar[x][y] = false;

				if(!canDelete())
					pillar[x][y] = true;
			}
			else if(a == 1 && b == 1) {
				// 양 끝이 보로 연결되어 있거나 한쪽 끝이 기둥인 경우
				if((beam[x - 1][y] && beam[x + 1][y]) || pillar[x][y - 1] || pillar[x + 1][y - 1])
					beam[x][y] = true;
			}
			else { // a == 1 && b == 0
				beam[x][y] = false;

				if(!canDelete())
					beam[x][y] = true;
			}
		}

		ArrayList<int[]> list = new ArrayList<>();

		for(int i=1;i<=N+1;i++)
			for(int j=1;j<=N+1;j++) {
				if(pillar[i][j])
					list.add(new int[] {i - 1, j - 1, 0});
				if(beam[i][j])
					list.add(new int[] {i - 1, j - 1, 1});
			}
		
		list.sort((o1, o2) -> {
			if(o1[0] == o2[0] && o1[1] == o2[1])
				return o1[2] - o2[2];
			else if(o1[0] == o2[0])
				return o1[1] - o2[1];
			else
				return o1[0] - o2[0];
		});

		int[][] ans = new int[list.size()][3];

		for(int i=0;i<ans.length;i++)
			ans[i] = list.get(i);

		return ans;
	}

	static boolean canDelete() {
		for(int i=1;i<=N+1;i++)
			for(int j=1;j<=N+1;j++)
				if(pillar[i][j] && !canPillar(i, j))
					return false;
				else if(beam[i][j] && !canBeam(i, j))
					return false;
		return true;
	}

	static boolean canPillar(int x, int y) {
		// 바닥이거나 기둥, 보 위인 경우 기둥 설치 가능
		if(y == 1 || pillar[x][y - 1] || beam[x][y] || beam[x - 1][y])
			return true;
		return false;
	}

	static boolean canBeam(int x, int y) {
		// 양 끝이 보로 연결되어 있거나 한쪽 끝이 기둥인 경우 보 설치 가능
		if((beam[x - 1][y] && beam[x + 1][y]) || pillar[x][y - 1] || pillar[x + 1][y - 1])
			return true;
		return false;
	}
}
