package com.via.util;

import javax.servlet.http.HttpServletRequest;

import com.via.controller.Action;

public class UrlUtility {

	public static String getActionUrl(HttpServletRequest request,
			Action... actions) {

		StringBuffer url = new StringBuffer();

		if (actions != null) {
			for (Action action : actions) {
				url.append("/").append(action.toString().toLowerCase());
			}
		}
		return url.toString();
	}

	public static String getActionUrl(HttpServletRequest request,
			String[] params, Action... actions) {

		StringBuffer url = new StringBuffer();

		if (actions != null) {
			for (Action action : actions) {
				url.append("/").append(action.toString().toLowerCase());
			}
		}

		if (params != null) {
			for (String param : params) {
				url.append("/").append(param);
			}
		}

		return url.toString();
	}

	public static String getRequestUrlParameter(String[] strArray, int pos) {

		if (strArray!=null && pos < strArray.length) {
			return strArray[pos];
		}
		return null;
	}

}