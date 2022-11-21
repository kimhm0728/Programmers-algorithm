package Kakao;

public class Kakao2021_3_2 {

	public static void main(String[] args) {
		String[] logs = {"01:20:15-01:45:14", 
				"00:40:31-01:00:00", 
				"00:25:50-00:48:29", 
				"01:30:59-01:53:29", 
				"01:37:44-02:02:30"};
		
		System.out.println(solution("02:03:55", "00:14:15", logs));
	}
    
	static String solution(String play_time, String adv_time, String[] logs) {
        if(play_time.equals(adv_time))
        	return "00:00:00";
        
        int play = strToSec(play_time);
        int adv = strToSec(adv_time);
        
        int[] time = new int[play + 1];
        
        for(String str : logs) {
        	String[] temp = str.split("-");
        	int start = strToSec(temp[0]);
        	int end = strToSec(temp[1]);
        	
        	for(int i=start;i<end;i++)
        		time[i]++;
        }
        
        long sum = 0;
        
        for(int i=0;i<adv;i++)
        	sum += time[i];
        
        long max = sum;
        
        int ans = 0;
        
        for(int i=0,j=adv;j<play;i++,j++) {
        	// 1초씩 구간 옮기기
        	sum -= time[i];
        	sum += time[j];
        	
        	if(max < sum) {
        		max = sum;
        		ans = i + 1;
        	}
        }
        
        return secToStr(ans);
    }
	
	static int strToSec(String str) {
		String[] arr = str.split(":");
		
		return Integer.parseInt(arr[0]) * 3600 + 
				Integer.parseInt(arr[1]) * 60 + 
				Integer.parseInt(arr[2]);
	}
	
	static String secToStr(int sec) {
		// HH
		String str = sec / 3600 > 9 ? "" : "0";
		str += String.valueOf(sec / 3600) + ":";
		sec %= 3600;
		
		// MM
		str += sec / 60 > 9 ? "" : "0";
		str += String.valueOf(sec / 60) + ":";
		sec %= 60;
		
		// SS
		str += sec > 9 ? "" : "0";
		str += String.valueOf(sec);

		return str;
	}
}
