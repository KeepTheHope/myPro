package test;

import java.util.HashSet;
import java.util.Set;

public class TestString {
	public static void main(String[] args) {
		String str = "我呢在城南呀";
		Set<String> set = new HashSet<String>();
		String[] arr = {"啊","呀","呢"};
		for (String string : arr) {
			set.add(string);
		}
		for (String string : arr) {
			if(str.contains(string)) {
				str = str.replaceAll(string,"");
			}
		}
		System.err.println(str);
		
		
		
		
	}
	
	

}
