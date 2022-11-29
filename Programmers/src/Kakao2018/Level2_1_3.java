package Kakao2018;

import java.util.*;

public class Level2_1_3 {
	// 2018 KAKAO BLIND RECRUITMENT 1차 Level 2 세 번째 문제 뉴스 클러스터링
	
	public static void main(String[] args) {
		System.out.println(solution("FRANCE", "french"));
	}
	
    static int solution(String str1, String str2) {
        // 자른 문자열, 출현횟수
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        
        // 모두 소문자로 변경
        stringCut(str1.toLowerCase(), map1);
        stringCut(str2.toLowerCase(), map2);
        
        if(map1.size() == 0 && map2.size() == 0)
            return 65536; // 1 * 65536
        
        int union = getUnion(map1, map2);
        int intersection = getIntersection(map1, map2);
        
        return (int)(((double)union / intersection) * 65536);
    }
    
    // 두 글자씩 끊어서 map에 넣기
    static void stringCut(String str, HashMap<String, Integer> map) {
        for(int i=0;i<str.length() - 1;i++) {
            char now = str.charAt(i);
            char next = str.charAt(i + 1);
            
            if(now < 'a' || now > 'z')
                continue;
            if(next < 'a' || next > 'z') {
                i++;
                continue;
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append(now).append(next);
            map.put(sb.toString(), map.getOrDefault(sb.toString() , 0) + 1);
        }
    }
    
    // 교집합의 개수 구하기
    static int getUnion(HashMap<String, Integer> map1, HashMap<String, Integer> map2) {
        int union = 0;
        
        for(String s : map1.keySet()) {
            if(!map2.containsKey(s)) 
                continue;
            // 두 집합에 모두 존재한다면 원소의 개수가 적은 것을 포함
            union += Math.min(map1.get(s), map2.get(s));
        }
        
        return union;
    }
    
    // 합집합의 개수 구하기
    static int getIntersection(HashMap<String, Integer> map1, HashMap<String, Integer> map2) {
        int intersection = 0;
        
        for(String s : map1.keySet()) {
            if(!map2.containsKey(s)) 
                intersection += map1.get(s);
            else {
                // 두 집합에 모두 존재한다면 원소의 개수가 많은 것을 포함
                intersection += Math.max(map1.get(s), map2.get(s));
                map2.remove(s);
            }
        }
        
        // map1과 겹치는 걸 삭제했기 때문에 바로 더해줌
        for(int i : map2.values()) 
            intersection += i;
        
        return intersection;
    }

}
