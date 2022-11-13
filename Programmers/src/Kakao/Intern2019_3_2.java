package Kakao;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Intern2019_3_2 {
	static HashSet<HashSet<String>> ans = new HashSet<>();

	public static void main(String[] args) {
		String[] u = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] b = {"fr*d*", "abc1**"};

		System.out.println(solution(u, b)); // 3
	}

	static int solution(String[] user_id, String[] banned_id) {
		DFS(new LinkedHashSet<>(), user_id, banned_id);

		return ans.size();
	}

	static void DFS(HashSet<String> set, String[] user_id, String[] banned_id) {
		if(set.size() == banned_id.length) {
			if(isMapping(set, banned_id))
				ans.add(new HashSet<>(set));
			return;
		}

		for(String str : user_id) {
			if(set.add(str)) {
				DFS(set, user_id, banned_id);
				set.remove(str);
			}
		}

	}

	static boolean isMapping(HashSet<String> set, String[] banned_id) {
		int idx = 0;
		for(String str : set) {
			if(str.length() != banned_id[idx].length())
				return false;
			for(int i=0;i<banned_id[idx].length();i++) {
				if(banned_id[idx].charAt(i) == '*')
					continue;
				if(str.charAt(i) != banned_id[idx].charAt(i))
					return false;
			}
			idx++;
		}
		return true;
	}

}
