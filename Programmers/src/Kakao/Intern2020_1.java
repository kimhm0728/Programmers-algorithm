package Kakao;

public class Intern2020_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "right"));
	}
	
    static String solution(int[] numbers, String hand) {
        int arr[] = {4, 1, 1, 1, 2, 2, 2, 3, 3, 3};
        
        int left = 4, right = 4;
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<numbers.length;i++) {
            int n = numbers[i];
            
            if(n == 1 || n == 4 || n == 7) {
                left = arr[n];
                sb.append("L");
            }
            else if(n == 2 || n == 5 || n == 8 || n == 0) {
                int tmpL = left < 0 ? Math.abs(-left - arr[n]) : Math.abs(left - arr[n]) + 1;
                int tmpR = right < 0 ? Math.abs(-right - arr[n]) : Math.abs(right - arr[n]) + 1;
                
                if(tmpL < tmpR || (tmpL == tmpR && hand.equals("left"))) {
                    left = -arr[n];
                    sb.append("L");
                }
                else if(tmpL > tmpR || (tmpL == tmpR && hand.equals("right"))) {
                    right = -arr[n];
                    sb.append("R");
                }
            }
            else {
                right = arr[n];
                sb.append("R");
            }
        }
        
        return sb.toString();
    }

}
