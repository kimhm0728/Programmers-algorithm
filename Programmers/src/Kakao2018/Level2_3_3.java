package Kakao2018;

import java.util.*;

public class Level2_3_3 {
	// 2018 KAKAO BLIND RECRUITMENT 3차 Level 2 세 번째 문제 압축

	static HashMap<String, Integer> dic = new HashMap<>();
	static ArrayList<Integer> list = new ArrayList<>();
	static int last = 26; // 마지막 색인 번호

	public static void main(String[] args) {
		for(int i : solution("KAKAO"))
			System.out.print(i + " ");
	}

	static int[] solution(String msg) {
		char c = 'A';

        // A~Z 사전에 추가
		for(int i=0;i<26;i++)
			dic.put(Character.toString((char)(c + i)), i + 1);

		compression(0, msg, msg.length());

		int size = list.size();
		int[] ans = new int[size];

		for(int i=0;i<size;i++)
			ans[i] = list.get(i);
        
		return ans;
	}

	static void compression(int idx, String msg, int n) {
		String w = ""; // 사전에 존재하고 현재 입력과 일치하는 가장 긴 문자열
		StringBuilder wc = new StringBuilder(); // w + 입력에서 처리되지 않은 다음 글자 (c)
		int i;

		for(i=idx;i<n;i++) {
			wc.append(msg.charAt(i));
			if(!dic.containsKey(wc.toString())) 
				break;
			else 
				w = wc.toString();
		}

		list.add(dic.get(w));
		if(!dic.containsKey(wc.toString()))
			dic.put(wc.toString(), ++last);

		if(i < n)
			compression(i, msg, n);
	}
	
}