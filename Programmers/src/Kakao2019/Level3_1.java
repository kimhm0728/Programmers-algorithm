package Kakao2019;

import java.util.*;

public class Level3_1 {

	public static void main(String[] args) {
		System.out.println(solution("Blind",
				new String[] {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"}));
	}

	static int solution(String word, String[] pages) {
		ArrayList<Page> list = new ArrayList<>();
		word = word.toLowerCase();

		for(int i=0;i<pages.length;i++) {
			String page = pages[i];
			String url = findUrl(page);

			if(url.equals(""))
				continue;

			int score = findWord(page, word);
			String[] outlink = findOutlink(page);
			Page p = new Page(i, url, score, outlink);
			list.add(p);
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
		
		// 1. 매칭 점수 내림차순 2. 인덱스 오름차순 정렬
		list.sort((o1, o2) -> Double.compare(o1.match, o2.match) == 0 ? o1.idx - o2.idx :
			Double.compare(o2.match, o1.match));

		return list.get(0).idx;
	}

	static String findUrl(String str) {
		String delim = "<meta property=\"og:url\" content=\"";
		int start = str.indexOf(delim) + delim.length();
		int end = str.indexOf("\"", start);

		return str.substring(start, end);
	}

	static int findWord(String str, String word) {
		// body 부분만 추출
		str = str.substring(str.indexOf("<body>"), str.indexOf("</body>"));

		// 알파벳을 제외한 모든 문자는 빈칸으로 교체
		str = str.toLowerCase().replaceAll("[^a-z]", " ");
		
		StringTokenizer st = new StringTokenizer(str);
		int score = 0;

		// 단어 단위로 자르기
		while(st.hasMoreTokens()) {
			if(st.nextToken().equals(word))
				score++;
		}

		return score;
	}

	static String[] findOutlink(String str) {
		String delim_s = "<a href=\"";
		String delim_e = "\">";
		ArrayList<String> list = new ArrayList<>();

		int start = 0; int end = 0;
		while(str.contains(delim_s)) {
			start = str.indexOf(delim_s) + delim_s.length();
			end = str.indexOf(delim_e, start);

			String link = str.substring(start, end);
			list.add(link);
			str = str.substring(end);
		}
		return list.toArray(new String[0]);
	}

	static class Page {
		String url;
		int score, idx, outlink_cnt;
		double match = 0;
		String[] outlink;

		Page(int idx, String url, int score, String[] outlink) {
			this.idx = idx;
			this.url = url;
			this.score = score; // 기본 점수
			this.match = score; // 매칭 점수의 default는 기본 점수 
			this.outlink = outlink; // 외부 링크가 담긴 배열
			this.outlink_cnt = outlink.length; // 외부 링크 갯수
		}

		void addLinkScore(double link) {
			this.match += link;
		}
	}
}
