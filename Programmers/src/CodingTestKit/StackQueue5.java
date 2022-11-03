package CodingTestKit;

import java.util.LinkedList;

public class StackQueue5 {

    static class Bridge {
        int weight;
        int sec;
        
        public Bridge(int weight, int sec) {
            this.weight = weight;
            this.sec = sec;
        }
        
        void addSec() {
            sec++;
        }
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

    static int solution(int bridge_length, int weight, int[] truck_weights) {
    	LinkedList<Bridge> bridge = new LinkedList<>();

    	int answer = 1;
    	int bridgeWeight = truck_weights[0];
    	int bridgeNum = 1;
    	int idx = 0;

    	bridge.offer(new Bridge(truck_weights[0], 1));

    	while(!bridge.isEmpty()) {
            // 다리 지나기
    		if(bridge.get(0).sec == bridge_length) {
    			bridgeWeight -= bridge.poll().weight;
    			bridgeNum--;
    		}
    		
            // 1초씩 더하기
    		for(int i=0;i<bridge.size();i++) 
    			bridge.get(i).addSec();
    		
            // 다리 건너기
    		if((idx + 1) < truck_weights.length && 
    				(bridgeWeight + truck_weights[idx + 1]) <= weight && 
    				(bridgeNum + 1) <= bridge_length) {
    			idx++;
    			bridgeWeight += truck_weights[idx];
    			bridgeNum++;
    			bridge.offer(new Bridge(truck_weights[idx], 1));
    		}
    		
            answer++;
        }
        
        return answer;
    }

}