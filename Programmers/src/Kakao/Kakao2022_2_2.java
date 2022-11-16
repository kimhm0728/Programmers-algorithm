package Kakao;

import java.util.*;

public class Kakao2022_2_2 {

	public static void main(String[] args) {
		int[] fees = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", 
				"07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", 
				"19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		
		int[] result = solution(fees, records);
		
		for(int i : result)
			System.out.println(i);
	}
	
    static int[] solution(int[] fees, String[] records) {
        TreeMap<Integer, ArrayList<String>> map = new TreeMap<>();
        StringTokenizer st;
        
        for(int i=0;i<records.length;i++) {
            st = new StringTokenizer(records[i]);
            
            String time = st.nextToken();
            int car = Integer.parseInt(st.nextToken());
            
            if(map.containsKey(car))
                map.get(car).add(time);
            else {
                ArrayList<String> list = new ArrayList<>();
                list.add(time);
                map.put(car, list);
            }
        }
        
        int[] result = new int[map.size()];
        
        int idx = 0;
        for (ArrayList<String> list : map.values()) {
            int time = 0;
            int size = list.size();
            
            for(int i=0;i<size;i+=2) {
                String in = list.get(i);
                String out;
                if(size % 2 != 0 && i == size - 1) // 출차된 내역 없으면
                    out = "23:59";
                else 
                    out = list.get(i + 1);
                
                time += timeCalcute(in, out);
            }
            
            if(time <= fees[0])
                result[idx++] = fees[1];
            else 
                result[idx++] = fees[1] + (int)Math.ceil((double)(time - fees[0]) / fees[2]) * fees[3];

        }
        
        return result;
    }
    
    static int timeCalcute(String in, String out) {
        StringTokenizer st = new StringTokenizer(in, ":");
        int inH = Integer.parseInt(st.nextToken());
        int inM = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(out, ":");
        int outH = Integer.parseInt(st.nextToken());
        int outM = Integer.parseInt(st.nextToken());
        
        int H, M;
        if(outM < inM) {
            M = 60 - (inM - outM);
            H = outH - inH - 1;
        }
        else {
            M = outM - inM;
            H = outH - inH;
        }
        
        return H * 60 + M;
    }

}
