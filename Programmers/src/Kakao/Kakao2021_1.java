package Kakao;

public class Kakao2021_1 {

	public static void main(String[] args) {
		System.out.println(solution("z-+.^."));
	}
	
    static String solution(String new_id) {
        // 1단계
        String ans = new_id.toLowerCase();
        
        StringBuilder sb = new StringBuilder(ans);
        
        for(int i=0;i<sb.length();i++) {
            char c = sb.charAt(i);
            // 2단계
            if((c < 'a' || c > 'z') && (c < '0' || c > '9') && c != '-' && c != '_' && c != '.')
                sb.setCharAt(i, '*');
        }
        
        ans = sb.toString().replaceAll("\\*", "");
    
        // 3단계
        ans = ans.replaceAll("\\.{2,}", "\\.");
        
        sb.setLength(0);
        sb.append(ans);
        
        // 4단계
        if(sb.charAt(0) == '.')
            sb.setCharAt(0, '*');
        if(sb.charAt(sb.length() - 1) == '.')
        	sb.setCharAt(sb.length() - 1, '*');
        
        ans = sb.toString().replaceAll("\\*", "");
        
        sb.setLength(0);
        sb.append(ans);
        
        // 5단계
        if(sb.length() == 0) 
            sb.append("a");
        // 6단계
        else if(sb.length() >= 16) {
            sb.setLength(15);
            if(sb.charAt(sb.length() - 1) == '.')
                sb.setCharAt(sb.length() - 1, '*');
            
            sb = new StringBuilder(sb.toString().replaceAll("\\*", ""));
        }
        // 7단계
        if(sb.length() <= 2)
            while(sb.length() < 3)
                sb.append(sb.charAt(sb.length() - 1));
        
        return sb.toString();
    }

}
