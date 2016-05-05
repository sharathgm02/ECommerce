package com.via.util;

public class StringUtility {

	private static String EMAIL_REGEX = "^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	public static Long parseToLong(String str, Long defaultValue) {

		try {
			if (str != null) {
				return Long.parseLong(str);
			}
			return defaultValue;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static Integer parseToInt(String str, int defaultValue) {

		try {
			if (str != null) {
				return Integer.parseInt(str);
			}
			return defaultValue;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static boolean isValidEmail(String email) {
		if (email != null && email.matches(EMAIL_REGEX)) {
			return true;
		}
		return false;
	}
}
