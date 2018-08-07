package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

enum Clour {
	RED, GREEN, BLUE
}

public class TestDemo2 {

	// static {
	// Runnable run = new Runnable() {
	// public void run() {
	// while (true) {
	// try {
	// TimeUnit.SECONDS.sleep(3);
	// // getTime();
	// } catch (Throwable e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// };
	// new Thread(run).start();
	// }

	public static Logger logger = Logger.getLogger(TestDemo2.class);

	public static void main(String[] args) throws Exception {
		// System.err.println(Clour.BLUE);
		// System.err.println(DateConst.周一.name());
		// System.err.println(DateConst.周一.ordinal());
		// System.err.println(DateConst.values());
		// for(int x = 0;x < DateConst.values().length;x ++) {
		// System.out.println(DateConst.values()[x]);
		// }

		List<String> list = new ArrayList<String>();
		list.add("d");
		list.add("a");
		list.add("ad");
		list.add("c");
		list.add("b");
		// list.forEach(System.out::println);
		Stream<String> stream = list.stream();
//		System.err.println(stream.filter((e) -> e.contains("a")).count());

		List<String> all = stream.filter((e) -> e.contains("a")).collect(Collectors.toList());
		System.out.println(all);

	}

	public static void getTime() {
		System.err.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
	}

	public static int add(String str, int... data) {
		int sum = 0;
		for (int x = 0; x < data.length; x++) {
			sum += data[x];
		}
		return sum;
	}

}
