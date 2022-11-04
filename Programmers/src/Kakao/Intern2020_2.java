package Kakao;

import java.util.ArrayList;
import java.util.List;

public class Intern2020_2 {
	static String[] prior = {"+-*", "+*-", "*-+", "*+-", "-+*", "-*+"};
    static long ans = Long.MIN_VALUE;
    static ArrayList<Long> num = new ArrayList<>();
    static ArrayList<Character> oper = new ArrayList<>();
    
	public static void main(String[] args) {
		System.out.println(solution("100-200*300-500+20"));
	}
	
    static long solution(String expression) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<expression.length();i++) {
            char c = expression.charAt(i);
            if(c >= '0' && c <= '9')
                sb.append(c);
            else {
                num.add(Long.parseLong(sb.toString()));
                oper.add(c);
                sb.setLength(0);
            }
        }
        num.add(Long.parseLong(sb.toString()));
        
        for(int i=0;i<prior.length;i++) {
        	System.out.println(prior[i] + " " + calcuteNum(prior[i]));
        	ans = Math.max(ans, calcuteNum(prior[i]));
        }
        return ans;
    }

    static long calcuteNum(String s) {
    	List<Long> tmpNum = new ArrayList<>(num);
    	List<Character> tmpOp = new ArrayList<>(oper);
    	
    	for(char c : s.toCharArray()) {
    		for(int i=0;i<tmpOp.size();i++) {
    			if(tmpOp.get(i) == c) {
    				switch(c) {
    				case '+':
    					tmpNum.set(i, tmpNum.get(i) + tmpNum.get(i + 1));
    					break;
    				case '*':
    					tmpNum.set(i, tmpNum.get(i) * tmpNum.get(i + 1));
    					break;
    				case '-':
    					tmpNum.set(i, tmpNum.get(i) - tmpNum.get(i + 1));
    					break;
    				}
    				tmpOp.remove(i);
    				tmpNum.remove(i + 1);
    				i--;
    			}
    		}
    	}
    	
    	return Math.abs(tmpNum.get(0));
    }

}
