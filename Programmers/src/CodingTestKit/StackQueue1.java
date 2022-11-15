package CodingTestKit;

import java.util.*;

public class StackQueue1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new int[] {1,1,3,3,0,1,1}));
	}

    public static int[] solution(int []arr) {
        Queue<Integer> q = new LinkedList<Integer>();
        
        q.offer(arr[0]);
        for(int i=1;i<arr.length;i++) {
            if(arr[i-1] == arr[i])
                continue;
            q.offer(arr[i]);
        }
        
        int[] answer = new int[q.size()];
        for(int i=0;i<answer.length;i++)
            answer[i] = q.poll();

        return answer;
    }
}
