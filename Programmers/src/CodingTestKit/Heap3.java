package CodingTestKit;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Heap3 {

	public static void main(String[] args) {
		int[] result = solution(new String[] {"I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"});

		System.out.println(result[0]);
		System.out.println(result[1]);
	}

	public static int[] solution(String[] operations) {
		PriorityQueue<Integer> min = new PriorityQueue<>();
		PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
		StringTokenizer st;
		int size = 0;

		for(int i=0;i<operations.length;i++) {
			st = new StringTokenizer(operations[i]);
			char oper = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());
            
            switch(oper) {
                case 'I':
                    min.add(num);
                    max.add(num);
                    size++;
                    break;
                case 'D':
                	if(size != 0) {
                		size--;
                        if(num == -1) {
                            int temp = min.poll();
                            max.remove(temp);
                        }
                        else if(num == 1) {
                            int temp = max.poll();
                            min.remove(temp);
                        }
                    }
                    break;
            }
        }
        
        if(size == 0) return new int[] {0, 0};
        
        int answer[] = new int[2];
        answer[0] = max.poll();
        answer[1] = min.poll();
        
        return answer;
    }

}