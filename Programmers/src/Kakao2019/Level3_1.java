package Kakao2019;

import java.util.*;

public class Level3_1 {

	public static void main(String[] args) {
		System.out.println(solution("Muzi",
				new String[] {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"}));

	}

	static int solution(String word, String[] pages) {
		HashMap<String, Page> map = new HashMap<>();
		double max = -1;
		int idx = 20;
		word = word.toLowerCase();

		for(int i=0;i<pages.length;i++) {
			String page = pages[i];
			Page p = new Page(i);
			String url = findUrl(page);
			map.put(url, p);
			
			findWord(page, word, p);
			findOutlink(page, p);
		}

		// 링크점수 계산하기
		for(Page p : map.values()) {
			for(String link : p.outlink) 
				if(map.containsKey(link))
					map.get(link).addLinkScore((double)p.score / p.outlink_cnt);
		}

		// 매칭점수가 가장 높은 웹페이지 구하기
		for(Page p : map.values()) {
			if(((double)p.score + p.link) >= max && p.idx < idx) {
				max = (double)p.score + p.link;
				idx = p.idx;
			}
		}

		return idx;
	}

	static String findUrl(String str) {
		String delim = "content=\"";
		int start = str.indexOf(delim) + delim.length();
		int end = str.indexOf("\"", start);
		
		return str.substring(start, end);
	}
	
	static void findWord(String str, String word, Page p) {
		String delim = "<body>";
		int start = str.indexOf(delim) + delim.length();
		delim = "</body>";
		int end = str.indexOf(delim, start);
		str = str.substring(start, end);
		
		while(true) {
			delim = "<a";
			start = str.indexOf(delim);
			if(start == -1)
				break;
			
			delim = "</a>";
			end = str.indexOf(delim);
			
			StringBuilder sb = new StringBuilder(str);
			sb.delete(start, end + delim.length());
			str = sb.toString();
		}
		
		str = str.toLowerCase().replaceAll("[^a-z]", " ");
		StringTokenizer st = new StringTokenizer(str);
		int score = 0;
		
		while(st.hasMoreTokens()) {
			if(st.nextToken().equals(word))
				score++;
		}
		
		p.setScore(score);
	} 
	
	static void findOutlink(String str, Page p) {
		String delim = "<a href=\"";
		int start = str.indexOf(delim);
		if(start == -1)
			return;
		
		start += delim.length();
		delim = "\">";
		int end = str.indexOf(delim, start);
		
		String link = str.substring(start, end);
		p.addOutlink(link);
		p.addOutlinkCnt();
		
		findOutlink(str.substring(end + delim.length()), p);
	}
	
	static class Page {
		int idx;
		int score;
		double link = 0;
		int outlink_cnt = 0;
		ArrayList<String> outlink = new ArrayList<>();

		Page(int idx) {
			this.idx = idx;
		}

		void setScore(int score) {
			this.score = score;
		}

		void addOutlinkCnt() {
			outlink_cnt++;
		}

		void addOutlink(String link) {
			outlink.add(link);
		}

		void addLinkScore(double link) {
			this.link += link;
		}
	}
}
