package KakaoIntern;

public class Intern2021_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result[] = solution(new String[][] {
			{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, 
			{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, 
			{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, 
			{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, 
			{"POPXP", "XXXXX", "XXXXX", "XXXXX", "XXXXX"}
		});
		
		for(int i : result)
			System.out.print(i + " ");
	}

	static int[] solution(String[][] places) {
		int[][] beside = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
		int[][] diagonal = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
		int[][] far = {{0, -2}, {-2, 0}, {0, 2}, {2, 0}};

		int []result = {1, 1, 1, 1, 1};

		Loop: for(int i=0;i<5;i++) {
			char[][] arr = new char[5][5];
			for(int j=0;j<5;j++)
				for(int k=0;k<5;k++)
					arr[j][k] = places[i][j].charAt(k);

			for(int j=0;j<5;j++)
				for(int k=0;k<5;k++) {
					if(arr[j][k] == 'P') {
						int newX, newY;
						
						// 바로 옆
						for(int l=0;l<4;l++) {
							newX = j + beside[l][0];
							newY = k + beside[l][1];
							if(newX >= 0 && newX < 5 && newY >= 0 && newY < 5 
									&& arr[newX][newY] == 'P') {
								result[i] = 0;
								continue Loop;
							}
						}

						// 대각선
						for(int l=0;l<4;l++) {
							newX = j + diagonal[l][0];
							newY = k + diagonal[l][1];
							if(newX >= 0 && newX < 5 && newY >= 0 && newY < 5 
									&& arr[newX][newY] == 'P' && 
									(arr[j + beside[l][0]][k + beside[l][1]] == 'O' ||
									arr[j + beside[(l + 1) % 4][0]][k + beside[(l + 1) % 4][1]] == 'O')) {
								result[i] = 0;
								continue Loop;
							}
						}

						// 맨해튼 거리 2
						for(int l=0;l<4;l++) {
							newX = j + far[l][0];
							newY = k + far[l][1];
							if(newX >= 0 && newX < 5 && newY >= 0 && newY < 5 
									&& arr[newX][newY] == 'P'
									&& arr[j + beside[l][0]][k + beside[l][1]] == 'O') {
								result[i] = 0;
								continue Loop;
							}
						}
					}
				}
		}

		return result;
	}

}
