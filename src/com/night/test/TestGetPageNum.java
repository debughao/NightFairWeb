package com.night.test;

import com.nightfair.uitl.PageNumUitl;

public class TestGetPageNum {

	public static void main(String[] args) {
		System.out.println(PageNumUitl.pageNum);
		PageNumUitl.setPageNum(12);
	    System.out.println(PageNumUitl.pageNum);
		System.out.println(PageNumUitl.getNum("comments","seller_id",1));

	}

}
