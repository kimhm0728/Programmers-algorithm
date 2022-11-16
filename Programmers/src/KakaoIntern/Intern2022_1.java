package KakaoIntern;

import java.util.ArrayList;
import java.util.HashMap;

public class Intern2022_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] s = {"AN", "CF", "MJ", "RT", "NA"};
		int[] c = {5, 3, 2, 7, 5};

		System.out.println(solution(s, c));
	}

	static class Node {
		char type;
		int score = 0;

		Node(char type) {
			this.type = type;
		}

		void addScore(int score) {
			this.score += score;
		}
	}

	public static String solution(String[] survey, int[] choices) {
		int[] score = {0, 3, 2, 1, 0, 1, 2, 3};
		char[] type = {'A', 'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'}; // ÀÎµ¦½º 0 ¹«½Ã
		HashMap<Character, Integer> map = new HashMap<>();

		for(int i=0;i<type.length;i++)
			map.put(type[i], i);

		ArrayList<Node> list[] = new ArrayList[5];

		for(int i=1;i<5;i++) {
			list[i] = new ArrayList<>();
			list[i].add(new Node(type[i * 2 - 1]));
			list[i].add(new Node(type[i * 2]));
		}

		for(int i=0;i<survey.length;i++) {
			int idx1, idx2;
			char t;
			int c = choices[i];

			if(c < 4) 
				t = survey[i].charAt(0);
			else if(c == 4) 
				continue;
			else 
				t = survey[i].charAt(1);

			int tmp = map.get(t);
			idx1 = tmp % 2 == 0 ? tmp / 2 : (tmp + 1) / 2;
			idx2 = tmp % 2 == 0 ? 1 : 0;

			list[idx1].get(idx2).addScore(score[c]);
		}

		String ans = "";
		for(int i=1;i<5;i++) {
			list[i].sort((o1, o2) -> o1.score == o2.score ? o1.score - o2.score : o2.score - o1.score);
			ans += list[i].get(0).type;
		}

		return ans;
	}

}
