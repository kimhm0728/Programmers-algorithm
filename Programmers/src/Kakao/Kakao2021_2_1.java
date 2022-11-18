package Kakao;

import java.util.*;

public class Kakao2021_2_1 {
	static HashMap<String, ArrayList<Integer>> map = new HashMap<>();
	
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
		for(int i=0;i<info.length;i++) 
			DFS(info[i].split(" "), "", 0);
		
		int n = query.length;
		int result[] = new int[n];
		
		// 코딩 테스트 점수를 기준으로 오름차순 정렬
		for(ArrayList<Integer> list : map.values()) 
			Collections.sort(list);
		
		for(int i=0;i<n;i++)
			result[i] = BinarySearch(query[i]);
		
		return result;
	}
	
	// 가능한 모든 문자열 조합하기 (key: 문자열 / value: 코딩 테스트 점수)
	static void DFS(String[] info, String str, int depth) {
		if(depth == 4) {
			int score = Integer.parseInt(info[depth]);
			if(map.containsKey(str)) {
				map.get(str).add(score);
			}
			else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(score);
				map.put(str, list);
			}
			return;
		}
		
		DFS(info, str + "-", depth + 1);
		DFS(info, str + info[depth], depth + 1);
	}
	
	// 코딩 테스트 점수를 이분 탐색하여 조건을 만족하는 인원 수 찾기
	static int BinarySearch(String query) {
		String[] arr = query.split(" and ");
		int score = Integer.parseInt(arr[3].split(" ")[1]);

		query = arr[0] + arr[1] + arr[2] + arr[3].split(" ")[0];
		
		if(!map.containsKey(query))
			return 0;
		
		ArrayList<Integer> list = map.get(query);
		int start = 0;
		int end = list.size();
		
		// score 이상인 값이 처음으로 나타나는 인덱스 찾기(하한선)
		while(start < end) { 
			int mid = (start + end) / 2;
			
			if(list.get(mid) >= score)
				end = mid;
			else // list.get(mid) > score
				start = mid + 1;
		}
		
		return list.size() - start;
	}

}