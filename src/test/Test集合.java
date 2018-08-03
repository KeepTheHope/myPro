package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test集合 {

	public static void main(String[] args) {

		// Set<String> result = new HashSet<String>();
		// Set<String> set1 = new HashSet<String>() {
		// {
		// add("王者荣耀");
		// add("英雄联盟");
		// add("穿越火线");
		// add("地下城与勇士");
		// }
		// };
		//
		// Set<String> set2 = new HashSet<String>() {
		// {
		// add("王者荣耀");
		// add("地下城与勇士");
		// add("魔兽世界");
		// }
		// };
		//
		//
		// Spliterator<String> spliterator = set1.spliterator();
		// int characteristics = spliterator.characteristics();
		// System.err.println(characteristics);

		// result.clear();
		// result.addAll(set1);
		// result.retainAll(set2);
		// System.out.println("交集：" + result);
		//
		// result.clear();
		// result.addAll(set1);
		// result.removeAll(set2);
		// System.out.println("差集：" + result);
		//
		// result.clear();
		// result.addAll(set1);
		// result.addAll(set2);
		// System.out.println("并集：" + result);
		// String a = "1";
		// switch (a) {
		// case "0":
		// System.err.println("hello");
		// break;
		//
		// default:
		// System.err.println("world");
		// break;
		// }
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		map.put("d", "4");
		map.put("e", "5");
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			System.err.println(it.next().getValue());
		}

		Set<String> set2 = map.keySet();
		System.err.println(set2);
		Iterator<String> iter = set2.iterator();
		while (iter.hasNext()) {
			String str = iter.next();
			if (str.equals("a") || str.equals("b") || str.equals("c")) {
				iter.remove();
			}
		}
		System.err.println(set2);
	}
}
