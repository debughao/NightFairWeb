package com.night.test;

import java.util.Random;

public class CreateNickName {

	public static void main(String[] args) {
	       RandomHan han = new RandomHan();
	       String nickname="用户"+2*han.getRandomHan();
	        System.out.print(nickname);
	    }
	     
	}
	class RandomHan {
	    private Random ran = new Random();
	    private final static int delta = 0x9fa5 - 0x4e00 + 1;
	      
	    public char getRandomHan() {
	        return (char)(0x4e00 + ran.nextInt(delta)); 
	    }
	}
