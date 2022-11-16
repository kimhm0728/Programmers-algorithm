package CodingTestKit;

import java.util.Arrays;
import java.util.Collections;

public class Sort3 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {3,0,6,1,5}));
	}
	
    public static int solution(int[] citations) {
        int cnt = 1;
        int answer = 0;
        
        Integer[] arr = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Collections.reverseOrder());
        
        while(cnt - 1 < arr.length) {
            if(arr[cnt - 1] >= cnt)
                answer = cnt;
            else
            	break;
            cnt++;
        }
        
        return answer;
    }

}
