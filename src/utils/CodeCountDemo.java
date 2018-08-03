package utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 统计代码行数
 *
 */
public class CodeCountDemo {
	// 定义一个集合操作，保存代码行的信息统计量，但是不统计空行代码
	private static Map<String, Long> map = new HashMap<String, Long>();

	public static void listCount(File file) throws Exception { // 进行代码行的统计
		if (file.isDirectory()) { // 文件是一个目录
			File results[] = file.listFiles();
			for (int x = 0; x < results.length; x++) {
				listCount(results[x]);
			}
		} else {
			if (file.isFile()) { // 如果现在是文件则判断后缀类型
				String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
				switch (ext) {
				case "java": {
					long count = map.get("java"); // 获得已经保存的行数
					map.put("java", count + rowCount(file));
					break;
				}
				case "jsp": {
					long count = map.get("jsp"); // 获得已经保存的行数
					map.put("jsp", count + rowCount(file));
					break;
				}
				case "js": {
					long count = map.get("js"); // 获得已经保存的行数
					map.put("js", count + rowCount(file));
					break;
				}
				case "css": {
					long count = map.get("css"); // 获得已经保存的行数
					map.put("css", count + rowCount(file));
					break;
				}
				case "html": {
					long count = map.get("html"); // 获得已经保存的行数
					map.put("html", count + rowCount(file));
					break;
				}
				case "json": {
					long count = map.get("json"); // 获得已经保存的行数
					map.put("json", count + rowCount(file));
					break;
				}
				default:{
					long count = map.get("other"); // 获得已经保存的行数
					map.put("other", count + rowCount(file));
					
				}
				}
			}
		}
	}

	public static long rowCount(File file) throws Exception {
		long count = 0L;
		Scanner scan = new Scanner(file);
		scan.useDelimiter("\n");
		while (scan.hasNext()) {
			if (scan.next().trim().length() > 0) {
				count++;
			}
		}
		scan.close();
		return count;
	}

	public static void main(String[] args) throws Exception {
		map.put("java", 0L); // 设置一个统计的初始化内容
		map.put("jsp", 0L); 
		map.put("js", 0L);
		map.put("css", 0L);
		map.put("html", 0L);
		map.put("json", 0L);
		map.put("other", 0L);
		File file = new File("D:" + File.separator + "Dev_tool" + File.separator + "my_tool_workspace"  + File.separator + "rbc_web");
		listCount(file);
		System.out.println("【机器人项目代码统计】" + "\r\n" + "Java代码行数===" + "[" + map.get("java") + "]");
		System.out.println("JSP代码行数===" + "[" + map.get("jsp") + "]");
		System.out.println("JS代码行数===" + "[" + map.get("js") + "]");
		System.out.println("CSS代码行数===" + "[" + map.get("css") + "]");
		System.out.println("HTML代码行数===" + "[" + map.get("html") + "]");
		System.out.println("JSON代码行数===" + "[" + map.get("json") + "]");
		System.out.println("其他代码行数===" + "[" + map.get("other") + "]");
	}

}
