package com.vankrimpen.dustin;

import java.util.LinkedList;
import java.util.List;

/*
 * Task 2)
 * IntStack is an implementation of Stack<Integer>
 * it uses a Linked List to store Integers 
 * 
 */
public class IntStack extends Stack<Integer> {
	public IntStack(List<Integer> intList) {
		super(intList);
	}
	
	public IntStack() {
		this.list = new LinkedList<Integer>();
	}
	
}
