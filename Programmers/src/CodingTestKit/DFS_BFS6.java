package CodingTestKit;

public class DFS_BFS6 {
    static String result = "";
    static StringBuilder sb = new StringBuilder();
    static boolean[] visit;
    
   public static void main(String[] args) {
      String[] arr = solution(new String[][] 
            {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}});
   
      for(String s : arr)
          System.out.println(s);
   }

    public static String[] solution(String[][] tickets) {
        visit = new boolean[tickets.length];
        
        sb.append("ICN ");
        DFS(0, "ICN", tickets);
        
        String[] answer = result.split(" ");

        return answer;
    }
    
    static void DFS(int depth, String start, String[][] arr) {
       if(depth == arr.length) {
          result = (!result.equals("") && result.compareTo(sb.toString()) < 0) ? result : sb.toString();
          return;
       }
       
       for(int i=0;i<arr.length;i++) {
          if(!visit[i] && arr[i][0].equals(start)) {
             visit[i] = true;
             sb.append(arr[i][1]).append(" ");
             DFS(depth + 1, arr[i][1], arr);
             
             sb.delete(sb.length() - 4, sb.length());
             visit[i] = false;
          }
       }
        
    }
   
}