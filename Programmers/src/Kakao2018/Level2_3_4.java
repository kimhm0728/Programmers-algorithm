package Kakao2018;

import java.util.*;

public class Level2_3_4 {
	// 2018 KAKAO BLIND RECRUITMENT 3차 Level 2 네 번째 문제 방금그곡

	static String ans = "";
	
	public static void main(String[] args) {
		String[] musicinfos = {"03:00,03:05,FOO,ABCDEF"};
		System.out.println(solution("ABCDEF", musicinfos));
	}
	
    static String solution(String m, String[] musicinfos) {
    	String target = replaceMusic(m);
    	
    	for(int i=0;i<musicinfos.length;i++) {
            String[] music = getMusic(musicinfos[i]);
            isSameMusic(target, music[0], music[1]); // 네오가 기억한 멜로디, 제목, 재생된 음
        }
    	
        if(ans.equals(""))
        	return "(None)";
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
        
        if(time <= len)
            ret[1] = music.substring(0, time);
        else {
        	StringBuilder sb = new StringBuilder();
        	sb.append(music);
        	
        	// substring으로 변경하기
        	for(int i=len;i<time;i++) {
        		sb.append(music.charAt(i % len));
        	}
        	
        	ret[1] = sb.toString();
        }
        
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
    	    	ans = ans.length() < title.length() ? title : ans;
    	    	return;
    		}
    	}
    }

}
