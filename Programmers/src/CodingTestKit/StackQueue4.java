package CodingTestKit;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

public class StackQueue4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new int[] {2,1,3,2}, 2));
	}
	
    static int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<int []> q = new LinkedList<>();
        
        for(int i=0;i<priorities.length;i++)
            q.offer(new int[] {priorities[i], i});
        
        while(!q.isEmpty()) {
            int[] i = q.poll();
            int value = i[0];
            int loc = i[1];
            
            Iterator<int[]> iter = q.iterator();
            boolean flag = false;
            
            // 큰 값 있는지 검사
            while(iter.hasNext()) {
                if(value < iter.next()[0]) {
                    flag = true;
                    q.offer(i);
                    break;
                }
            }
            
            if(!flag) {
                answer++;
                if(location == loc)
                    break;
            }
        }
        return answer;
    }

}
