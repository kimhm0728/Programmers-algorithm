package CodingTestKit;

import java.util.Arrays;

public class Hash3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new String[] {"119", "97674223", "1195524421"}));
	}
	
    public static boolean solution(String[] phone_book) {
        
        Arrays.sort(phone_book);
        
        for(int i=1;i<phone_book.length;i++) 
            if(phone_book[i].startsWith(phone_book[i-1]))
                return false;
        
        return true;
    }

}
