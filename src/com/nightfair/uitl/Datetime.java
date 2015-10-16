package com.nightfair.uitl;

import java.text.DateFormat;
import java.util.Date;

public class Datetime {
	public static String getNow() {
		DateFormat d1 = DateFormat.getDateTimeInstance();
		String str1 = d1.format(new Date());
		return str1;
	}

	public static void main(String[] args) {
		System.out.println(getNow());
	}
}
