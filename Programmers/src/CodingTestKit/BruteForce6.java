package CodingTestKit;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class BruteForce6 {
    static ArrayList<Integer> list[];
    static boolean visit[];
    
	public static void main(String[] args) {
		System.out.println(solution(9, new int[][] {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}));
	}
	
    static int solution(int n, int[][] wires) {
        list = new ArrayList[n + 1];
        visit = new boolean[n + 1];
        int ans = Integer.MAX_VALUE;
        
        for(int i=1;i<list.length;i++)
            list[i] = new ArrayList<>();
        
        for(int i=0;i<wires.length;i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            
            list[a].add(b);
            list[b].add(a);
        }
        
        for(int i=0;i<wires.length;i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            
            list[a].remove(list[a].indexOf(b));
            list[b].remove(list[b].indexOf(a));
            
            int child = BFS(a);
            Arrays.fill(visit, false);
            
            ans = Math.min(ans, Math.abs(child - (n - child)));
            
            list[a].add(b);
            list[b].add(a);
        }
        
        return ans;
    }
    
    static int BFS(int start) {
        Queue<Integer> q = new LinkedList<>();
        
        int cnt = 1;
        q.offer(start);
        visit[start] = true;
        
        while(!q.isEmpty()) {
            int num = q.poll();
            
            for(int i : list[num]) {
                if(!visit[i]) {
                    cnt++;
                    q.offer(i);
                    visit[i] = true;
                }
            }
        }
        
        return cnt;
    }
}
