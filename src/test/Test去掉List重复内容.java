package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 去掉list重复内容
 *
 */
public class Test去掉List重复内容 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("c");
		System.err.println(getNewList(list));
		removeDuplicateWithOrder(list);
	}

	// 1、通过循环进行删除
	public static void removeDuplicate(List<String> list) {
		HashSet<String> h = new HashSet<String>(list);
		list.clear();
		list.addAll(h);
		System.out.println(list);

	}

	// 2、通过HashSet删除
	public static void removeDuplicate1(List<String> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					list.remove(j);
				}
			}
		}
		System.out.println(list);

	}

	// 3、删除重复元素，并保持顺序。
	public static void removeDuplicateWithOrder(List<String> list) {
		Set<String> set = new HashSet<String>();
		List<String> newList = new ArrayList<String>();
		for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
			String element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		list.clear();
		list.addAll(newList);
		System.out.println(" remove duplicate " + list);
	}

	//
	public static List<String> getNewList(List<String> li) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < li.size(); i++) {
			String str = li.get(i); // 获取传入集合对象的每一个元素
			if (!list.contains(str)) { // 查看新集合中是否有指定的元素，如果没有则加入
				list.add(str);
			}
		}
		return list; // 返回集合
	}

}
