package com.vankrimpen.dustin;

import java.util.List;

/*
 * Abstract generic class Stack allows for stacks of any object type 
 * 
 */
public abstract class Stack<T> {
	// Any type of List may be used to hold the stack 
	protected List<T> list;
	
	/*
	 * Stack can be constructed from a pre-existing list or no list
	 */
	public Stack(List<T> list) {
		this.list = list;
	}
	public Stack() {}
	
	// return true if stack contains no elements 
	public boolean isEmpty() {
		return list.isEmpty();
	}
	// push element to top of stack 
	public void push(T obj) {
		list.add(obj);
	}
	
	//Takes a list and pushes all elements in the list to the stack 
	public void pushAll(List<T> list) {
		for (T obj : list) {
			push(obj);
		}
	}
	// remove top element from stack
	public void pop() {
		list.remove(list.size()-1);
	}
	// return top element of stack
	public T peek() {
		return list.get(list.size()-1);
	}
	
}
