package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parse {
	public static void main(String[] args) {
		String html = "<html><p><span style=\"font-family: 隶书, SimLi;\"><img src=\"rbc/resource/201806271530071442905071.jpg\" title=\"201806271530071442905071.jpg\" alt=\"BingWallpaper-2018-06-24.jpg\"/><embed src=\"rbc/resource/201806271530071449979082.mp4\" width=\"420\" height=\"280\" wmode=\"transparent\" play=\"true\" loop=\"false\" menu=\"false\" allowscriptaccess=\"never\" allowfullscreen=\"true\"/><embed src=\"rbc/resource/201806271530071454109085.ogg\" width=\"420\" height=\"280\" wmode=\"transparent\" play=\"true\" loop=\"false\" menu=\"false\" allowscriptaccess=\"never\" allowfullscreen=\"true\"/></span><img src=\"http://img.baidu.com/hi/face/i_f17.gif\"/><span style=\"font-family: 隶书, SimLi;\">啊哈哈哈</span></p>/<html>";
				Document doc = Jsoup.parse(html);
				System.err.println(doc.getElementsByTag("p").size());
				System.out.println(doc.getElementsByTag("p"));
				System.err.println(doc);
		
		
	}

}
