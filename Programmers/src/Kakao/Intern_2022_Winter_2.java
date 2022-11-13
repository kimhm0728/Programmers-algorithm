package Kakao;

import java.util.*;

public class Intern_2022_Winter_2 {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<>();
		list.add(1);
		for(int i=0;i<31;i++)
			list.add(100);
		list.add(1);
		System.out.println(solution(list, 2));
	}
    
    public static int solution(List<Integer> cost, int x) {
    // Write your code here
        int mod = (int)Math.pow(10, 9) + 7;
        int ans = 0;
        
        for(int i=cost.size()-1;i>=0;i--) {
            int paint = cost.get(i);
            if(x >= paint) {
                x -= paint;
                ans = (ans + ((int)Math.pow(2, i) % mod)) % mod;
            }
        }
        
        return (int)(ans % mod);
        
    	/*
        int mod = (int)Math.pow(10, 9) + 7;
        BigInteger ans = new BigInteger("0");
        
        for(int i=cost.size()-1;i>=0;i--) {
            int paint = cost.get(i);
            if(x >= paint) {
                x -= paint;
                ans.add(BigDecimal.valueOf(Math.pow(2, i) % mod).toBigInteger());
                ans.remainder(BigInteger.valueOf(mod));
            }
        }
        
        return ans.remainder(BigInteger.valueOf(mod)).intValue();
        */
    }
    
}
