package Kakao;

import java.util.*;

public class Kakao2019_2_2 {
	static ArrayList<String> uniKey = new ArrayList<>();
	static ArrayList<String> cdKey = new ArrayList<>();
	static int n, m;
	static boolean[] visit;
	static String[][] rel;

	public static void main(String[] args) {
		String[][] relation = { {"a","1","aaa","c","ng"},
				{"a","1","bbb","e","g"},
				{"c","1","aaa","d","ng"},
				{"d","2","bbb","d","ng"}};

		System.out.println(solution(relation));
	}

	static int solution(String[][] relation) { 
		n = relation.length; // 튜플 개수
		m = relation[0].length; // 애트리뷰트 개수

		visit = new boolean[m];
		rel = relation;

		DFS(0, new StringBuilder());

		uniKey.sort((o1, o2) -> o1.length() == o2.length() ? 
				o1.compareTo(o2) : o1.length() - o2.length());

		// 유일성을 만족하는 키 중 최소성을 만족하는 키를 list에 추가
		for(int i=0;i<uniKey.size();i++)
			if(isMin(i))
				cdKey.add(uniKey.get(i));

		return cdKey.size();
	}

	static void DFS(int start, StringBuilder sb) {
		for(int i=start;i<m;i++) {
			if(!visit[i]) {
				visit[i] = true;
				sb.append(i);

				String tuple = sb.toString();
				// 유일성을 만족한다면 list에 추가
				if(isUnique(tuple))
					uniKey.add(tuple);

				if(i + 1 != m)
					DFS(i + 1, sb);
				visit[i] = false;
				sb.setLength(sb.length() - 1);
			}
		}
	}

	static boolean isMin(int idx) {
		String target = uniKey.get(idx);
		HashSet<Integer> set = new HashSet<>();
		
		for(char c : target.toCharArray())
			set.add(c - '0');

		// 검사하려는 키가 후보키를 포함한다면 유일성을 만족하지 못함
		for(String s : cdKey) {
			int check = 0;
			for(char c : s.toCharArray()) {
				int i = c - '0';
				if(!set.contains(i))
					break;
				check++;
			}
			if(check == s.length())
				return false;
		}

		return true;
	}
	
	static boolean isUnique(String s) {
		// 문자열, 출현 개수
		HashMap<String, Integer> map = new HashMap<>();

		for(int i=0;i<n;i++) {
			StringBuilder sb = new StringBuilder();
			for(char c : s.toCharArray()) {
				sb.append(rel[i][c - '0']);
			}
			String tuple = sb.toString();
			map.put(tuple, map.getOrDefault(tuple, 0) + 1);
		}

		// 개수 둘 이상이라면 유일하지 않은 것
		for(int i : map.values())
			if(i > 1) return false;

		return true;
	}

}
