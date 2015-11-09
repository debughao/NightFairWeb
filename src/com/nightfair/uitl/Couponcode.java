package com.nightfair.uitl;

import java.util.UUID;

public class Couponcode {
	public static String CreateCouponcode() {
		UUID uuid = UUID.randomUUID();
		// 得到对象产生的ID
		String a = uuid.toString();
		// 转换为大写
		a = a.toUpperCase();
		// 替换 -
		a = a.replaceAll("-", "");
		a = a.substring(0, 16);
		return a;
	}
}
