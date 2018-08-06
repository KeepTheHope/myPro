package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConvertorUtil {

	private static Map<String, List<Object>> mapToList(Map<Object, Object> map) {

		Map<String, List<Object>> resultMap = new HashMap<String, List<Object>>();
		// 将Map Key 转化为List
		List<Object> mapKeyList = new ArrayList<Object>(map.keySet());
		// 将Map Key 转化为List
		List<Object> mapValuesList = new ArrayList<Object>(map.values());
		resultMap.put("keyList", mapKeyList);
		resultMap.put("valueList", mapValuesList);
		return resultMap;

	}

	private static Map<String, Set<Object>> mapToSet(Map<Object, Object> map) {
		Map<String, Set<Object>> resultMap = new HashMap<String, Set<Object>>();
		// 将Map 的键转化为Set
		Set<Object> mapKeySet = map.keySet();
		// 将Map 的值转化为Set
		Set<Object> mapValuesSet = new HashSet<Object>(map.values());
		resultMap.put("keySet", mapKeySet);
		resultMap.put("valueSet", mapValuesSet);
		return resultMap;
	}

	// 数组-->Set
	private static Set<Object> arrayToSet(Object[] arr) {
		Set<Object> set = new HashSet<Object>(Arrays.asList(arr));
		return set;
	}

	// Set-->数组
	private static String setToArray(Set<Object> set) {

		Object[] arr = new Object[set.size()];
		set.toArray(arr);
		return Arrays.toString(arr);
	}

	// List-->Set
	private static Set<Object> listToSet(List<Object> list) {
		Set<Object> set = new HashSet<Object>(list);
		return set;
	}

	// Set --> List
	private static List<Object> setToList(Set<Object> set) {
		List<Object> list = new ArrayList<Object>(set);
		return list;
	}

	// List-->数组
	private static String listToArray(List<Object> list) {
		Object[] objects = list.toArray();// 返回Object数组
		String[] arr = new String[list.size()];
		list.toArray(arr);// 将转化后的数组放入已经创建好的对象中
		return Arrays.toString(arr);
	}

	// 数组-->List
	private static List<Object> arrayToList(Object[] arr) {
		return Arrays.asList(arr);
	}

}