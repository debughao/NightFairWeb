package com.nightfair.uitl;
/**
 * 
* @ClassName: Matches
* @Description: TODO(登录方式判断)
* @author debughao
* @date 2015年9月19日
 */
public class Matches {
	private static String USERNAME = "^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9_\u4E00-\u9FA5]{2,10}$";
	private static String PHONE = "^1[3|4|5|8][0-9]\\d{4,8}$";
	private static String EMAIL= "^\\w+[@]\\w+((.com)|(.net)|(.cn)|(.org)|(.gmail))$";
	public static boolean isPhone(String s) {
		return s.matches(PHONE);
	}
	public static boolean isUsename(String s) {
		return s.matches(USERNAME);
	}
	public static boolean isEmail(String s) {
		return s.matches(EMAIL);
	}
}
