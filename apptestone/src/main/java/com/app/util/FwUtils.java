package com.app.util;

public class FwUtils {

	/**
	 * Use to convert text into Upper case
	 * */
	public static String toInitCap(String stringToInitCap) {
		StringBuilder sb = new StringBuilder();
		for (String string : stringToInitCap.trim().split("[^a-zA-Z0-9]")) {
			if (string.length() != 0) {
				string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
				sb.append(string);
			}
		}
		return sb.toString();
	}
	/**
	 * Use to convert text Upper case with space
	 * */
	public static String toInitCapWithSpace(String stringToInitCap) {
		StringBuilder sb = new StringBuilder();
		for (String string : stringToInitCap.trim().split("[^a-zA-Z0-9]")) {
			if (string.length() != 0) {
				string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
				sb.append(string + " ");
			}
		}
		return sb.toString().trim();
	}

	/*
	 * Use to convert into lower case
	 * **/
	public static String toCamelCase(String stringToInitCap) {
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for (String string : stringToInitCap.trim().split("[^a-zA-Z0-9]")) {
			if (string.length() != 0) {
				if (!flag) {
					sb.append(toInitLower(string));
					flag = true;
				} else {
					sb.append(toInitCap(string));
				}

			}
		}
		return sb.toString();
	}
	/*
	 * Use to convert into lower case
	 * **/
	public static String toInitLower(String stringToInitCap) {
		StringBuilder sb = new StringBuilder();
		for (String string : stringToInitCap.trim().split("[^a-zA-Z0-9]")) {
			if (string.length() != 0) {
				string = string.substring(0, 1).toLowerCase() + string.substring(1);
				sb.append(string);
			}
		}
		return sb.toString();
	}
}
