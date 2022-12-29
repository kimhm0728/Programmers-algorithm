package Kakao;

public class Kakao2017_2_1 {
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int cnt;
    static int max;
    static int width;
    static boolean[][] visit;
    static int[][] pictures;
    static int M, N;
    
	public static void main(String[] args) {
		int[][] picture = {{1, 1, 1, 0}, 
				{1, 2, 2, 0}, 
				{1, 0, 0, 1}, 
				{0, 0, 0, 1}, 
				{0, 0, 0, 3}, 
				{0, 0, 0, 3}};
		
		int[] result = solution(6, 4, picture);
		System.out.println(result[0] + " " + result[1]);
	}
	
    static int[] solution(int m, int n, int[][] picture) {
        cnt = 0; max = 0;
        M = m; N = n; pictures = picture;
        visit = new boolean[m][n];

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(picture[i][j] != 0 && !visit[i][j]) { // 색칠되어 있고 아직 방문하지 않은 곳이면 새로운 영역을 의미
                    width = 0;
                    visit[i][j] = true; 
                    DFS(i, j, picture[i][j]); // 색칠된 위치와 값으로 DFS
                    max = Math.max(width, max); // DFS로 탐색한 영역의 넓이를 max와 비교해 대입
                    cnt++; // 영역의 갯수 더하기
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = cnt;
        answer[1] = max;
        return answer;
    }
    
    static void DFS(int x, int y, int value) {
        width++; // DFS를 진행할 때마다 넓이 1씩 증가
        
        for(int i=0;i<4;i++) {
            // 상하좌우로 탐색
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            
            // 탐색하려는 위치가 범위 내에 존재하고 방문하지 않았으며, 현재 영역의 값과 같은 값으로 색칠되어 있다면
            if(isNext(nextX, nextY) && !visit[nextX][nextY] && pictures[nextX][nextY] == value) {
                visit[nextX][nextY] = true;
                DFS(nextX, nextY, value); 
            }
        }
    }
    
    // 탐색하려는 곳이 picture 배열 내에 있는지 확인
    static boolean isNext(int x, int y) {
        if(x < M && x >= 0 && y < N && y >= 0)
            return true;
        return false;
    }

}
