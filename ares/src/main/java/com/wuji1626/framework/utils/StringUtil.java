package com.wuji1626.framework.utils;

public class StringUtil {

	public static String makeInitialCharacterUpperCase(String words){
		String first = words.substring(0, 1).toUpperCase();
		String rest = words.substring(1, words.length());
		return new StringBuffer(first).append(rest).toString();
	}
	public static String makeInitialCharaterLowerCase(String words){
		String first = words.substring(0, 1).toLowerCase();
		String rest = words.substring(1, words.length());
		return new StringBuffer(first).append(rest).toString();
	}
}
