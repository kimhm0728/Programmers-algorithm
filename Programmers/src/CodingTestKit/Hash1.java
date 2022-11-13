package CodingTestKit;

import java.util.Arrays;

public class Hash1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new String[] {"leo", "kiki", "eden"}, new String[] {"leo", "eden"}));
	}
	
    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        for(int i=0;i<participant.length;i++) {
            if(i>=completion.length || !participant[i].equals(completion[i])) {
                answer = participant[i];
                break;
            }
        }
        
        return answer;
    }

}