package CodingTestKit;

import java.util.PriorityQueue;
import java.util.Iterator;

public class Heap1 {
    static PriorityQueue<Integer> q = new PriorityQueue<>();
    
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        for(int i=0;i<scoville.length;i++)
            q.add(scoville[i]);
        
        while(q.peek() < K) {
            answer++;
            
            if(q.size() < 2)
                return -1;
            
            int first = q.poll();
            int second = q.poll();
            
            q.add(first + second * 2);
        }
        return answer;
    }
}
