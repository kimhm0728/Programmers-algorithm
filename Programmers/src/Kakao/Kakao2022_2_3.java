package Kakao;

import java.util.ArrayList;

public class Kakao2022_2_3 {
	static ArrayList<int[]> list = new ArrayList<>();
	static int[] result = new int[11];
	static boolean[] visit = new boolean[11];
	static int max = -1;

	public static void main(String[] args) {
		int[] result = solution(9, new int[] {0,0,1,2,0,1,1,1,1,1,1});
		for(int i : result)
			System.out.println(i);
	}

	static int[] solution(int n, int[] info) {
		DFS(0, n, info);

		if(list.size() == 0)
			return new int[] {-1};

		list.sort((o1, o2) -> {
			for(int i=10;i>=0;i--)
				if(o1[i] != o2[i])
					return o2[i] - o1[i];
			return 0;
		});

		return list.get(0);
	}

	static void DFS(int start, int cnt, int[] info) {
		if(cnt == 0) {
			int diff = getScoreDiff(info, result);
			if(diff > 0) {
				if(max == diff)
					list.add(result.clone());
				else if(max < diff) {
					max = diff;
					list.clear();
					list.add(result.clone());
				}
			}
			return;
		}

		for(int i=start;i<11;i++) {
			if(!visit[i]) {
				visit[i] = true;
				int arrow = info[i] + 1; // 어피치보다 하나 더 쏘기
				if(arrow > cnt) 
					arrow = cnt; // 쏠 화살이 부족할 경우 남은 화살 모두 쏘기
				result[i] = arrow;
				DFS(i, cnt - arrow, info);
				visit[i] = false;
				result[i] = 0;
			}
		}
	}

	// 라이언이 이길 경우 점수의 차이를, 어피치가 이길 경우 -1를 반환
	static int getScoreDiff(int[] info, int[] result) {
		int score[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		int apeach = 0;
		int ryan = 0;

		for(int i=0;i<11;i++) {
			if(info[i] == 0 && result[i] == 0)
				continue;
			else if(info[i] >= result[i])
				apeach += score[i];
			else 
				ryan += score[i];
		}

		if(apeach >= ryan)
			return -1;

		return ryan - apeach;
	}

}
