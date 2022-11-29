package Kakao2018;

import java.util.*;

public class Level2_1_1 {
	// 2018 KAKAO BLIND RECRUITMENT 1차 Level 2 첫 번째 문제 캐시

	static int[] pre, now, next;
	static int size = 0;
	public static void main(String[] args) {
		String[] cities = {"a", "b", "a"};
		System.out.println(solution(3, cities));
	}
	
	static int solution(int cacheSize, String[] cities) {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>(cacheSize, 0.75f, true) {
			@Override
			protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
				return size() > cacheSize;
			}
		};
		
		int ans = 0;

		for(String s : cities) {
			String city = s.toLowerCase();
			ans += map.containsKey(city) ? 1 : 5;
			map.put(city, 0);
		}
		
		return ans;
    }

}
