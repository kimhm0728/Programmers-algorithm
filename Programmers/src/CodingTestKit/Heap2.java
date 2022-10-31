package CodingTestKit;
import java.util.PriorityQueue;
import java.util.Comparator;

class Heap2 {
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{0, 3}, {1, 9}, {2, 6}}));
	}
	
    public static int solution(int[][] jobs) {
        int time[] = new int[jobs.length];
        boolean visit[] = new boolean[jobs.length];

        // 요청시점, 처리시간
        PriorityQueue<int[]> wait = new PriorityQueue<>(new Comparator<int[]>() {
        	@Override
        	public int compare(int[] i1, int[] i2) {
        		return i1[1] - i2[1];
        	}
        });

        for(int i=0;i<jobs.length;i++)
        	wait.add(new int[] {jobs[i][0], jobs[i][1]});

        // 인덱스, 요청시점, 완료시간
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
        	@Override
        	public int compare(int[] i1, int[] i2) {
        		return i1[2] - i2[2];
        	}
        });

        int sec = 0;
        int idx = 1;
        int[] a = wait.poll();
        q.add(new int[] {0, a[0], a[1]});
        int preProcess = 0;

        while(!visit[jobs.length - 1]) {
        	if(!q.isEmpty() && q.peek()[2] == sec) {
        		int[] arr = q.poll();
        		time[arr[0]] = arr[2] - arr[1];
        		visit[arr[0]] = true;
        		preProcess = arr[2];
        	}

            if(idx < jobs.length && visit[idx - 1]) {
            	int i[] = wait.poll();
                q.add(new int[] {idx, i[0], preProcess + i[1]});
                idx++;
            }
            
            sec++;
        }
        
        int sum = 0;
        for(int i=0;i<time.length;i++)
            sum += time[i];
        
        return sum / time.length;
    }
}