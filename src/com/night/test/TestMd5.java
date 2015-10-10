package com.night.test;

import com.nightfair.uitl.MD5Util;

public class TestMd5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
      System.out.println("Md5之后"+MD5Util.MD5("123456abc")); 
      System.out.println("加密之后"+MD5Util.convertMD5("123456")); 
      System.out.println("解密之后"+MD5Util.convertMD5(MD5Util.convertMD5("123456"))); 
	}

}
