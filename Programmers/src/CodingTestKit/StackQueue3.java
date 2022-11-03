package CodingTestKit;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;


public class StackQueue3 {

	public static void main(String[] args) {
		int[] result = solution(new int[] {95, 80, 75, 99, 80, 99},
				new int[] {1, 1, 1, 1, 1, 1});
		
		for(int i : result)
			System.out.print(i + " ");
	}

    static int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Queue<Integer> q = new LinkedList<Integer>();
        
        for(int i=0;i<speeds.length;i++) {
            int day = (int)Math.ceil((double)(100 - progresses[i]) / speeds[i]);
            q.offer(day);
        }
        
        int cnt = 1;
        int preDay = q.poll();
        
        while(!q.isEmpty()) {
            int nowDay = q.poll();
            if(preDay < nowDay) {
                result.add(cnt);
                cnt = 1;
                preDay = nowDay;
            }
            else
                cnt++;
        }
        result.add(cnt);
        
        int[] answer = new int[result.size()];
        for(int i=0;i<answer.length;i++)
            answer[i] = result.get(i);
        
        return answer;

    }
}