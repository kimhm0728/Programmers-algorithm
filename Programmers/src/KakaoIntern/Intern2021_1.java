package KakaoIntern;

import java.util.HashMap;

public class Intern2021_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution("one4seveneight"));
	}
	
    static int solution(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        String strArr[] = {"zero", "one", "two", "three", "four",
                     "five", "six", "seven", "eight", "nine"};
        
        for(int i=0;i<strArr.length;i++) 
            map.put(strArr[i], i);
        
        String tmp = "";
        String result = "";
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c >= '0' && c <= '9') {
                result += Character.toString(c);
            }
            else if(c >= 'a' && c <= 'z') {
                tmp += Character.toString(c);
                if(map.containsKey(tmp)) {
                	result += map.get(tmp);
                	tmp = "";
                }
            }
        }
        
        return Integer.parseInt(result);
    }

}
