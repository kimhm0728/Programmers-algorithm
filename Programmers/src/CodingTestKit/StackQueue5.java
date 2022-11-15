package CodingTestKit;

import java.util.LinkedList;

public class StackQueue5 {

	static class Bridge {
		int sec = 1;
		int weight;

		public Bridge(int weight) {
			this.weight = weight;
		}

		public void addSec() {
			this.sec++;
		}
	}

	public static void main(String[] args) {
		System.out.println(solution(2, 10, new int[] {7,4,5,6}));
	}

	static int solution(int bridge_length, int weight, int[] truck_weights) {
		LinkedList<Bridge> list = new LinkedList<>();

		int sec = 1;
		int idx = 0;
		int cnt = 0;
		int nowWeight = 0;
		int nowLen = 0;

		while(cnt < truck_weights.length) {
			if(!list.isEmpty() && list.peek().sec == bridge_length) {
				nowWeight -= list.poll().weight;
				nowLen--;
				cnt++;
			}

			for(Bridge b : list) 
				b.addSec();

			if(idx < truck_weights.length 
					&& nowWeight + truck_weights[idx] <= weight 
					&& nowLen + 1 <= bridge_length) {
				nowWeight += truck_weights[idx];
				nowLen++;
				list.add(new Bridge(truck_weights[idx++]));
			}
			sec++;
		}

		return sec - 1;
	}

}