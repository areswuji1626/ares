package com.wuji1626.framework.utils;

import java.util.List;

public class ValidateUtil {
	public static boolean isNull(String str){
		if(str == null){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isBlank(String str){
		if(str == null || str.length()==0){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isBlank(List<String> list){
		if(list == null || list.size()==0)
			return true;
		else
			return false;
	}
	public static boolean isNull(Object obj){
		if(obj == null){
			return true;
		}else{
			return false;
		}
	}
}
