package CodingTestKit;

public class DFS_BFS1 {
	static int cnt = 0;
	static boolean visit[];

	public static void main(String[] args) {
		System.out.println(solution(new int[] {4, 1, 2, 1}, 4));
	}

	public static int solution(int[] numbers, int target) {
		visit = new boolean[numbers.length];

		DFS(numbers, target, 0);
		
		return cnt;
	}

	static void DFS(int[] arr, int target, int start) {
		for(int i=start;i<arr.length;i++) {
			if(!visit[i]) {
				visit[i] = true;
				makeTarget(arr, target);
				DFS(arr, target, i);
				visit[i] = false;
			}
		}
	}
	
	static void makeTarget(int[] arr, int target) {
		int num = 0;
		
		for(int i=0;i<arr.length;i++) 
			num += visit[i] ? -arr[i] : arr[i];
		
		if(target == num)
			cnt++;
	}
}
