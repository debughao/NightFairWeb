package com.night.test;

import com.nightfair.uitl.Matches;

public class TestMatch {

	public static void main(String[] args) {
		//长度为2-10个字符支持数字、字母、下划线、中文，字母或中文开头
		System.out.println("true为用户名"+Matches.isUsename("ss2221"));
		//常见手机号
		System.out.println("true为手机号"+Matches.isPhone("15737954118"));
		//邮箱
		System.out.println("true为邮箱"+Matches.isEmail("863260364@qq.com"));
	}

}
