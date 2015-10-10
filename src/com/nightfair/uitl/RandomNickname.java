package com.nightfair.uitl;

import java.util.Random;

public class RandomNickname {
	 private Random ran = new Random();
	    private final static int delta = 0x9fa5 - 0x4e00 + 1;	      
	    public char getRandomHan() {
	        return (char)(0x4e00 + ran.nextInt(delta)); 
	    }
}
