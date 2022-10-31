package CodingTestKit;

import java.util.Arrays;
import java.util.HashSet;

public class BruteForce3 {
    static int[] arr;
    static boolean[] visit;
    static int[] result;
    static int count = 0;
    static int max = Integer.MIN_VALUE;
    static HashSet<Integer> set = new HashSet<>();
    
	public static void main(String[] args) {
		System.out.println(solution("143"));
	}
    
    public static int solution(String numbers) {
        arr = new int[numbers.length()];
        visit = new boolean[numbers.length()];
        result = new int[numbers.length()];
        
        for(int i=0;i<numbers.length();i++)
            arr[i] = numbers.charAt(i) - '0';
        
        for(int i=1;i<=arr.length;i++) {
            Arrays.fill(visit, false);
            DFS(0, i);
        }
        
        return count;
    }
    
    static void DFS(int depth, int idx) {
        if(depth == idx) {
            isDemical(idx);
            return;
        }
        for(int i=0;i<arr.length;i++) {
            if(!visit[i]) {
                visit[i] = true;
                result[depth] = arr[i];
                DFS(depth + 1, idx);
                visit[i] = false;
            }
        }
    }
    
    static void isDemical(int size) {
        if(result[0] == 0) return;
        
        // 숫자 만들기
        int num = result[0], temp = 1;
        for(int i=1;i<size;i++) {
            temp *= 10;
            num += temp * result[i];
        } 
        
        if(set.contains(num)) return;
        else set.add(num);
        
        // 소수 판별
        if(num < 2) return;
        
        for(int i=2;i<=Math.sqrt(num);i++)
            if(num % i == 0) return;
        
        count++;
    }

}
