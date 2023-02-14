package programmers;

public class StringChanger {

	public static void main(String[] args) {
		String s = "[[1, 2], [1, 3], [1, 4], [1, 5], [5, 6], [5, 7], [5, 8]]";
		System.out.print(s.replaceAll("\\[", "\\{").replaceAll("\\]", "\\}"));
	}

}
