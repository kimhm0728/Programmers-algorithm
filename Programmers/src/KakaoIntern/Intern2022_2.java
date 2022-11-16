package KakaoIntern;

public class Intern2022_2 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {1,2,1,2}, new int[] {1,10,1,2})); // 7
	}

	static int solution(int[] queue1, int[] queue2) {
		int n = queue1.length;
		long sum1 = 0, sum2 = 0;
		long equal;
		int p1 = 0, p2 = n;
		int ans = 0;

		int[] arr = new int[n * 2];

		for(int i=0;i<n;i++) {
			sum1 += queue1[i];
			sum2 += queue2[i];

			arr[i] = queue1[i];
			arr[i + n] = queue2[i];
		}
		equal = (sum1 + sum2) / 2;
		
		while(p1 < 2 * n) {
			if(sum1 == equal)
				return ans;
			
			if(sum1 < sum2) {
				sum1 += arr[p2];
				sum2 -= arr[p2];
				p2 = p2 + 1 >= n * 2 ? 0 : p2 + 1;
			}
			else {
				sum2 += arr[p1];
				sum1 -= arr[p1];
				p1++;
			}
			ans++;
		}

		return -1;
	}

}
