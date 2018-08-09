package com.pan.common.utils;

public class StringUtils {
	
	public static boolean isNull(String str) {
		 if (str == null || str.trim().length() == 0) {
	            return true;
	        }
		return false;
	}
	
}
