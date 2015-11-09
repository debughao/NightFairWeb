package com.nightfair.uitl;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class Datetime {
	public static String getNow() {
		DateFormat d1 = DateFormat.getDateTimeInstance();
		String str1 = d1.format(new Date());
		return str1;
	}

	public static void main(String[] args) {
		System.out.println(getNow());
		 UUID uuid = UUID.randomUUID();
	      // 得到对象产生的ID
	      String a = uuid.toString();
	      // 转换为大写
	      a = a.toUpperCase();
	      // 替换 -
	      a = a.replaceAll("-", "");
	      a=a.substring(0, 16);
	      System.out.println(a);
	}
}
