package com.base.utl;

import java.text.NumberFormat;

public class StringUtil {

	private StringUtil() {
	}

	public static String comma(int number) {
		String formattedNumber = NumberFormat.getInstance().format(number);
		return formattedNumber;
	}

}