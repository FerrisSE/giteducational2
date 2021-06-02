package com.vankrimpen.dustin;

import java.util.ArrayList;

/*
 * Task 1)
 * CharStack is an implementation of Stack<Character> 
 * it uses an array list to store Characters 
 */
public class CharStack extends Stack<Character> {

	public CharStack(String chars) {
		super(Test.stringToCharList(chars));
	}
	
	public CharStack() {
		this.list = new ArrayList<Character>();
	}
	
}
