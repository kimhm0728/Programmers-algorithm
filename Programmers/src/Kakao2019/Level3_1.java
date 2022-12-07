package Kakao2019;

import java.util.*;

public class Level3_1 {

	public static void main(String[] args) {
		System.out.println(solution("Muzi",
				new String[] {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"}));

	}

	static int solution(String word, String[] pages) {
		ArrayList<Page> list = new ArrayList<>();
		double max = -1;
		int idx = 20;
		word = word.toLowerCase();
		
		for(int i=0;i<pages.length;i++) {
			String page = pages[i];
			String url = findUrl(page);
			
			Page p = new Page(url);
			if(url.equals(""))
				continue;
			list.add(p);
			
			findWord(page, word, p);
			findOutlink(page, p);
		}
		
		// 링크점수 계산하기
		for(int i=0;i<list.size();i++) {
			Page out = list.get(i);
			for(String link : out.outlink) {
				for(int j=0;j<list.size();j++) {
					Page in = list.get(j);
					if(j != i && in.url.equals(link))
						in.addLinkScore(out.score / (double)out.outlink_cnt);
				}	
			}
		}
		
		// 매칭점수가 가장 높은 웹페이지 구하기
		for(int i=0;i<list.size();i++) {
			Page p = list.get(i);
			if((p.score + p.link) > max) {
				max = p.score + p.link;
				idx = i;
			}
		}
		
		return idx;
	}

	static String findUrl(String str) {
		String delim = "<meta property=\"og:url\" content=\"";
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
		double score = 0.0;
		
		while(st.hasMoreTokens()) {
			if(st.nextToken().equals(word))
				score += 1.0;
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
		String url;
		double score;
		double link = 0;
		int outlink_cnt = 0;
		ArrayList<String> outlink = new ArrayList<>();

		Page(String url) {
			this.url = url;
		}

		void setScore(double score) {
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
