package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test正则 {

	public static void main(String[] args) throws FileNotFoundException {

//		String insertSQL = "INSERT INTO dept(deptno-no,dname-no,loc-no) " + " VALUES (#{deptno} ,#{dname} ,#{loc})";
//		String regex1 = "#\\{\\w+\\}"; // 拆分操作
//		Pattern pattern1 = Pattern.compile(regex1); // 编译正则
//		Matcher matcher1 = pattern1.matcher(insertSQL); // 定义要处理的字符串
//		while (matcher1.find()) { // 如果说现在发现有与之匹配的内容，则进行取出
//			System.out.println(matcher1.group(0).replaceAll("#\\{|\\}", ""));
//
//		}
		
		String str = "sss<a>刘伟</a><a>是个sb</a>sss<a>789</a>";
		String regex = "<a>([^<]+)</a>";
		Pattern pattern = Pattern.compile(regex); 
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) { // 如果说现在发现有与之匹配的内容，则进行取出
			System.out.println(matcher.group(0).replace("<a>", "").replace("</a>", ""));

		}
		
		
		
		
		
	}

	public static void test() throws FileNotFoundException {
		String str = "C:/Users/HYTX/Desktop/a.txt";
		File file = new File(str);
		InputStream input1 = new FileInputStream(file);
		Scanner sc = new Scanner(input1);
		String input = sc.nextLine();
		System.err.println(input);
		String regex = "^[\\u4e00-\\u9fa5]*$";
		Matcher m = Pattern.compile(regex).matcher(input);
		System.out.println(m.find());
		sc.close();
	}

	/**
	 * 处理语句去掉指定内容
	 * @param statement
	 * @return 处理后的内容
	 */
	public static String handleContent(String statement) {
		String flag = "[slot:";
		if (statement.contains(flag)) {
			statement = formatPattern2(statement);
		} else {
			statement = formatPattern1(statement);
		}
		return statement;
	}

	private static String formatPattern2(String str) {
		if (str == null || str.length() == 0)
			return "";
		String regex1 = "[slot:";
		String regex2 = "]";
		return str.replace(regex1, "").replace(regex2, "");
	}

	private static String formatPattern1(String str) {
		if (str == null || str.length() == 0)
			return "";
		Pattern pet1 = Pattern.compile("(\\[\\s*[w|W]\\s*\\:\\d+\\-\\d+\\s*\\])");
		Pattern pet2 = Pattern.compile("(\\d+)");
		Matcher m1 = pet1.matcher(str);
		Matcher m2 = null;
		List<String> list = new ArrayList<String>();
		String line = null;
		while (m1.find()) {
			System.out.println("匹配组结果：" + m1.group() + "\t" + m1.start() + "\t" + m1.end());
			line = m1.start() + "," + m1.end();
			m2 = pet2.matcher(m1.group());
			while (m2.find()) {
				line += "," + m2.group();
			}
			list.add(line);
		}
		Collections.reverse(list);
		String s1 = null;
		String s2 = null;
		for (String l : list) {
			String[] arr = l.split(",");
			s1 = str.substring(0, Integer.parseInt(arr[0]));
			s2 = str.substring(Integer.parseInt(arr[1]));
			str = s1 + "[u4e00-u9fa5]{" + arr[2] + "," + arr[3] + "}" + s2;
		}
		return str;
	}

	private static String formatPattern3(String str) {
		// String str = "[slot:localtion]";
		String re = "\\[slot\\:\\w+\\]";
		Pattern pa = Pattern.compile(re);
		Matcher ma = pa.matcher(str);
		while (ma.find()) {
			// System.err.println(ma.group(0).replaceAll("\\[|slot|\\:|\\]", ""));
			str = ma.group(0).replaceAll("\\[|slot|\\:|\\]", "");
		}
		return str;
	}

}
