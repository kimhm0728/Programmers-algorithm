package CodingTestKit;

public class BruteForce5 {
	static boolean visit[];
	static int result[];
	static int ans = -1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(80, new int[][] {
			{80, 20}, {50, 40}, {30, 10}
		}));
	}

	public static int solution(int k, int[][] dungeons) {
		visit = new boolean[dungeons.length];
		result = new int[dungeons.length];
		
		DFS(k, dungeons, 0);
		
		return ans;
	}
	
	static void DFS(int k, int[][] arr, int depth) {
		if(depth == arr.length) {
			ans = Math.max(ans, dungeonsNum(k, arr));
			return;
		}
		
		for(int i=0;i<arr.length;i++) {
			if(!visit[i]) {
				visit[i] = true;
				result[depth] = i;
				DFS(k, arr, depth + 1);
				visit[i] = false;
			}
		}
	}
	
	static int dungeonsNum(int k, int[][] arr) {
		int cnt = 0;
		
		for(int i=0;i<arr.length;i++) {
			if(k >= arr[result[i]][0]) {
				cnt++;
				k -= arr[result[i]][1];
			}
			else
				return cnt;
		}
		
		return cnt;
	}
}
