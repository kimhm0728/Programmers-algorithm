package Kakao2018;

import java.util.*;

public class Level2_3_4 {
	// 2018 KAKAO BLIND RECRUITMENT 3차 Level 2 네 번째 문제 방금그곡

    static String[][] alpha = {{"A#", "a"}, {"G#", "g"}, {"F#", "f"}, {"D#", "d"}, {"C#", "c"}};
    static String ans = "(None)";
    static int ansTime = 0;
	
	public static void main(String[] args) {
		String[] musicinfos = {"11:50,12:04,HELLO,CDEFGAB", "12:57,13:11,BYE,CDEFGAB"};
		System.out.println(solution("ABCDEFG", musicinfos));
	}
	
    static String solution(String m, String[] musicinfos) {
    	String target = replaceMusic(m);
    	
    	for(int i=0;i<musicinfos.length;i++) {
            String[] music = getMusic(musicinfos[i]);
            isSameMusic(target, music[0], music[1]); // 네오가 기억한 멜로디, 제목, 재생된 음
        }
    	
        return ans;
    }
    
    static String[] getMusic(String str) {
    	StringTokenizer st = new StringTokenizer(str, ",");
        int start = strToInt(st.nextToken()); // 시작 시간
        int end = strToInt(st.nextToken()); // 끝 시간
        int time = end - start;
        
        String title = st.nextToken(); 
        String music = replaceMusic(st.nextToken()); // 곡의 음

        int len = music.length();
        String[] ret = new String[2];
        ret[0] = title;
        ret[1] = music.repeat(time / len) + music.substring(0, time % len);
        // 재생 시간을 곡 길이로 나눈 몫 만큼 반복, 반복 후 남은 시간이 있다면 붙이기
        
        return ret;
    }
    
    // HH:MM 형태의 시간을 분으로 변환
    static int strToInt(String str) {
        StringTokenizer st = new StringTokenizer(str, ":");
        return Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
    }
    
    // # 들어가는 음을 다른 알파벳으로 치환
    static String replaceMusic(String m) {
    	String ret = m;
    	for(String[] a : alpha)
            ret = ret.replaceAll(a[0], a[1]);
    	
        return ret;
    }
    
    static void isSameMusic(String target, String title, String music) {
        int len = music.length();
    	if(music.contains(target) && ansTime < len) {
            ans = title;
            ansTime = len;
        }
    }
}
