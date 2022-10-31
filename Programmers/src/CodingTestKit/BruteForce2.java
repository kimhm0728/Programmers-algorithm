package CodingTestKit;

import java.util.ArrayList;

public class BruteForce2 {

	public static void main(String[] args) {
		int[] arr = solution(new int[] {3, 3, 2, 1, 5});
		for(int i : arr)
			System.out.println(i);
	}

	public static int[] solution(int[] answers) {
		int[][] solve = {
				{1, 2, 3, 4, 5},
				{2, 1, 2, 3, 2, 4, 2, 5},
				{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
		};

		int[] cnt = new int[3];

		for(int i=0;i<3;i++) {
			for(int j=0;j<answers.length;j++) {
				if(solve[i][j % solve[i].length] == answers[j])
					cnt[i]++;
			}
		}

		int max = Math.max(cnt[0], Math.max(cnt[1], cnt[2]));
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i=0;i<3;i++)
			if(cnt[i] == max)
				list.add(i + 1);

		int answer[] = new int[list.size()];
		for(int i=0;i<answer.length;i++)
			answer[i] = list.get(i);

		return answer;
	}

}
