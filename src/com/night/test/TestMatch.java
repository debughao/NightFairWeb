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
		
		String[] str2=new String[]{"洛阳","广东省","fuj"};
		System.out.println(getPosition("广东省", str2));
		String s1 ="146路(启月街首末站-虎丘首末站)";
		int n =s1.indexOf("(");
		String s2=s1.substring(0, n);
		System.out.println(s2);
	}
	public static int getPosition(String str1,String[]str2){
		int n = 0;
		for (int i = 0; i < str2.length; i++) {
			if(str2[i]==str1){
				n=i;
				break;
			}
			
		}
		return n;		
	}
}
