package CodingTestKit;

import java.util.Stack;

public class StackQueue2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution("()()"));
	}
	
    static boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c == '(')
                stack.push(c);
            else if(!stack.empty())
                stack.pop();
            else
                return false;
        }

        return stack.empty() ? true : false; 
    }

}
