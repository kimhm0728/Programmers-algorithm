package Kakao2019;

import java.util.*;

public class Level3_2 {
	static ArrayList<Node> tree = new ArrayList<>();
	static ArrayList<Integer> pre = new ArrayList<>();
	static ArrayList<Integer> post = new ArrayList<>();
	
	public static void main(String[] args) {
		int[][] result = solution(new int[][] {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}});
		
		for(int[] arr : result) {
			for(int i : arr)
				System.out.print(i + " ");
			System.out.println();
		}
	}
	
    static int[][] solution(int[][] nodeinfo) {   	
    	int n = nodeinfo.length;
    	
    	for(int i=0;i<n;i++) 
    		tree.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
    	
    	tree.sort((o1, o2) -> o1.y == o2.y ? o1.x - o2.x : o2.y - o1.y);
        
    	int point = 1;
    	
    	// 레벨별로 arraylist에 담고 i레벨은 i+1레벨의 원소에서 가져오기
    	for(int i=0;i<n - 1;i++) {
    		int left = 0, right = 0;
    		Node now = tree.get(i);
    		Node first = tree.get(point);
    		
    		left = isLeft(now, first);
    		if(left == 0) 
    			right = isRight(now, first);
    		point = left != 0 || right != 0 ? point + 1 : point;
    		
    		if(i != n - 2 && left != 0) {
    			Node second = tree.get(point);
    			right = isRight(now, second);
    			point = right == 0 ? point : point + 1;
    		}
    		
    		now.setDir(left, right);
    	}
        
    	int[][] answer = new int[2][n];
    	preOrder(0);
    	postOrder(0);
    	
    	for(int i=0;i<n;i++) {
    		answer[0][i] = pre.get(i);
    		answer[1][i] = post.get(i);
    	}
    	
    	return answer;
    }
    
    static void preOrder(int idx) {
    	Node n = tree.get(idx);
    	
    	pre.add(n.idx);
    	if(n.left != 0 || (n.left == 0 && n.right != 0))
    		preOrder(idx + 1);
    	if(n.left != 0 && n.right != 0)
    		preOrder(idx + 2);
    }
    
    static void postOrder(int idx) {
    	Node n = tree.get(idx);
    	
    	if(n.left != 0 || (n.left == 0 && n.right != 0))
    		preOrder(idx + 1);
    	if(n.left != 0 && n.right != 0)
    		preOrder(idx + 2);
    	post.add(n.idx);
    }
    
    static class Node {
    	int idx;
    	int left = 0, right = 0;
    	int x, y;
    	
    	Node(int idx, int x, int y) {
    		this.idx = idx;
    		this.x = x;
    		this.y = y;
    	}
    	
    	void setDir(int left, int right) {
    		this.left = left;
    		this.right = right;
    	}
    }
    
    static int isLeft(Node now, Node next) {
    	if(next.y < now.y && next.x < now.x)
    		return next.idx;
    	return 0;
    }
    
    static int isRight(Node now, Node next) {
    	if(next.y < now.y && next.x > now.x)
    		return next.idx;
    	return 0;
    }

}
