package Kakao2018;

import java.util.Arrays;

public class Level2_3_2 {

	public static void main(String[] args) {
		String[] files = {"img000012345", "img1.png","img2","IMG02"};
		for(String str : solution(files))
			System.out.println(str);
	}
	
    static String[] solution(String[] files) {
        int n = files.length;
        String[][] file = new String[n][4];
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<n;i++) {
            file[i][0] = files[i];
            
            int cnt = 0;
            boolean flag = false;
            int idx = 1;
            for(int j=0;j<files[i].length();j++) {
            	char c = files[i].charAt(j);
                if(idx == 1 && (c < '0' || c > '9'))
                    sb.append(c);
                else if(c >= '0' && c <= '9' && !flag) {
                    if(idx == 1) {
                        file[i][idx++] = sb.toString();
                        sb.setLength(0);
                    }
                    if(++cnt > 5) {
                    	file[i][2] = sb.toString();
                    	file[i][3] = files[i].substring(j, files[i].length());
                    	break;
                    }
                    sb.append(c);
                }
                else if(c < '0' || c > '9' || flag) {
                    if(idx == 2) {
                        flag = true;
                        file[i][idx++] = sb.toString();
                        sb.setLength(0);
                    }
                    sb.append(c);
                }
            }
            if(flag) 
            	file[i][3] = sb.toString();
            else if(file[i][2] == null)
            	file[i][2] = sb.toString();
            sb.setLength(0);
        }
        
        Arrays.sort(file, (o1, o2) -> o1[1].toLowerCase().equals(o2[1].toLowerCase()) ? 
        		Integer.parseInt(o1[2]) - Integer.parseInt(o2[2]) : o1[1].compareTo(o2[1]));
        
        String[] answer = new String[n];
        for(int i=0;i<n;i++)
            answer[i] = file[i][0];
        
        return answer;
    }

}
