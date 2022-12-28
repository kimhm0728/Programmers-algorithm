package Kakao2018;

import java.util.*;

public class Level2_3_4 {
	// 2018 KAKAO BLIND RECRUITMENT 3차 Level 2 네 번째 문제 방금그곡

    static String ans = "(None)";
    static int ansTime = 0;
	
	public static void main(String[] args) {
		String[] musicinfos = {"11:50,12:04,HELLO,CDEFGAB", "12:57,13:11,BYE,CDEFGAB"};
		System.out.println(solution("ABCDEFG", musicinfos));
	}
	
    static String solution(String m, String[] musicinfos) {
    	String target = replaceMusic(m);
    	
    	for(String musicinfo : musicinfos) {
            String[] music = getMusic(musicinfo);
            isSameMusic(target, music[0], music[1]); // 네오가 기억한 멜로디, 제목, 재생된 음
        }
    	
        return ans;
    }
    
    static String[] getMusic(String str) {
    	StringTokenizer st = new StringTokenizer(str, ",");
        int start = strToInt(st.nextToken());
        int end = strToInt(st.nextToken());
        int time = end - start;
        
        String title = st.nextToken();
        String music = replaceMusic(st.nextToken());

        int len = music.length();
        String[] ret = new String[2];
        ret[0] = title;
        ret[1] = music.repeat(time / len) + music.substring(0, time % len);
        
        return ret;
    }
    
    static int strToInt(String str) {
        StringTokenizer st = new StringTokenizer(str, ":");
        return Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
    }
    
    static String replaceMusic(String m) {
    	String ret = m;
    	char alpha = 'A';
    	for(int i=0;i<7;i++) 
    		ret = ret.replaceAll(Character.toString(alpha + i) + "#", Character.toString(alpha + 32 + i));
    	
    	return ret;
    }
    
    static void isSameMusic(String target, String title, String music) {
    	Loop : for(int i=0;i<music.length();i++) {
    		if(music.charAt(i) == target.charAt(0)) {
    			for(int j=1;j<target.length();j++)
    				if(target.charAt(j) != music.charAt((i + j) % music.length()))
    					continue Loop;
    	    	if(ansTime < music.length()) {
                    ans = title;
                    ansTime = music.length();
                }
    	    	return;
    		}
    	}
    }
}
