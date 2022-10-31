package CodingTestKit;

public class BruteForce4 {

	public static void main(String[] args) {
		int[] ans = solution(24, 24);
		System.out.println(ans[0]);
		System.out.println(ans[1]);
	}
	
	public static int[] solution(int brown, int yellow) {
		for(int i=yellow;i>0;i--) 
			if(yellow % i == 0 && brown == (yellow / i + i) * 2 + 4)
				return new int[] {i + 2, yellow / i + 2};

		return null;
	}

}
