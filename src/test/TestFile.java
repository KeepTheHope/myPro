package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class TestFile {
	public static void main(String[] args) throws Exception {
		List<String> lines = new ArrayList<String>();
		List<String> list = new ArrayList<String> () ;
		list.add("今天天气怎么样");
		list.add("今天天气如何");
		list.add("今天天气好吗");
		list.add("今天下雨吗");
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() > 0) {
			for (int x = 0; x < list.size(); x++) {
				if (list.size() == 1) {
					String str = list.get(x);
					lines.add(str + "	" + "\"\"");
				}
				if(list.size() > 1) {
					String str = list.get(x);
					sb.append(str + "####||");
				}
			}
			lines.add(StringUtils.removeEnd(sb.toString(), "####||") +"	" + "\"\"");
		}
		
		writeFile("C:/Users/HYTX/Desktop/3.txt", lines);
	}
	private static void writeFile(String filePath, List<String> lines) throws IOException {
		if (StringUtils.isBlank(filePath))
			return;
		FileUtils.writeLines(new File(filePath), "UTF-8", lines);
	}
}
