package CodingTestKit;

public class BruteForce1 {

	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{60, 50}, {30, 70}, {60, 30}, {80, 40}}));
	}

	public static int solution(int[][] sizes) {
		int w = 0, h = 0;

		for(int i=0;i<sizes.length;i++) {
			w = Math.max(w, Math.max(sizes[i][0], sizes[i][1]));
			h = Math.max(h, Math.min(sizes[i][0], sizes[i][1]));
		}

		return w * h;
	}

}
