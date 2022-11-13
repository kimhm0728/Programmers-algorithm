package Kakao;

import java.util.*;

public class Intern_2022_Winter_3_1 {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(10);
		list.add(3);
		list.add(5);
		list.add(7);
		
		System.out.println(solution(list));
	}

    public static int solution(List<Integer> box) {
    // Write your code here
    	int n = box.size();
    	int[] arr = new int[n + 1];
    	int sum = 0;
    	
    	for(int i=0;i<n;i++) {
    		arr[i + 1] = box.get(i);
    		sum += arr[i + 1];
    	}
    	
    	int mid = (int)Math.ceil((double)sum / n);
    	
    	for(int i=n;i>1;i--) {
    		if(mid >= arr[i]) {
    			sum -= arr[i];
    			n--;
    			mid = (int)Math.ceil((double)sum / n);
    			continue;
    		}
    		int x = arr[i] - mid;
    		if(mid >= 1) {
    			arr[i] -= x;
    			arr[i - 1] += x;
    		} 
    	}
    	
    	return mid;
    }
    
    static int findMax(int[] arr) {
    	int idx = arr.length - 1;
    	int max = arr[idx];
    	
    	for(int i=idx-1;i>0;i--)
    		if(arr[i] > max) {
    			idx = i;
    			max = arr[i];
    		}
    	
    	return idx;
    }
}
