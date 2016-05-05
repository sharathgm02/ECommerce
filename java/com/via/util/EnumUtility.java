package com.via.util;

public class EnumUtility {

	public static <E extends Enum<E>> E parseEnum(String str,
			Class<E> enumClass, E defaultValue) {

		try {
			return (E) Enum.valueOf(enumClass, str.toUpperCase());
		} catch (Exception e) {
			return defaultValue;
		}
	}

}
