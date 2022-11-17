package Kakao;

import java.util.*;

public class Kakao2021_2_1 {

	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150",
				"python frontend senior chicken 210",
				"python frontend senior chicken 150",
				"cpp backend senior pizza 260",
				"java backend junior chicken 80",
		"python backend senior chicken 50"};

		String[] query = {"java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200",
				"cpp and - and senior and pizza 250",
				"- and backend and senior and - 150",
				"- and - and - and chicken 100",
		"- and - and - and - 150"};

		int[] result = solution(info, query);
		for(int i : result)
			System.out.print(i + " ");
	}

	static int[] solution(String[] info, String[] query) {
		StringTokenizer st;
		int n = info.length;
		String[][] infos = new String[n][5];

		for(int i=0;i<n;i++) {
			st = new StringTokenizer(info[i]);
			for(int j=0;j<5;j++)
				infos[i][j] = st.nextToken();
		}

		int ans[] = new int[query.length];
		for(int i=0;i<query.length;i++) {
			st = new StringTokenizer(query[i]);
			ArrayList<Integer> list = new ArrayList<>();

			// 가장 첫번째 기준인 언어가 동일한 경우 list에 넣기
			String language = st.nextToken();
			for(int j=0;j<infos.length;j++) {
				if(language.equals("-") || language.equals(infos[j][0]))
					list.add(j);
			}

			int idx = 1;
			for(int j=0;j<7;j++) {
				String str = st.nextToken();
				if(str.equals("and"))
					continue;
				if(str.equals("-")) {
					idx++;
					continue;
				}
				for(int k=0;k<list.size();k++) {
					int temp = list.get(k);
					if((j != 6 && !str.equals(infos[temp][idx]))
							|| (j == 6 && Integer.parseInt(str) > Integer.parseInt(infos[temp][idx]))) {
						list.remove(k);
						k--;
					}
				}
				idx++;

			}
			ans[i] = list.size();
		}

		return ans;
	}

}
