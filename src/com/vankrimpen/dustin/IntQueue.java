package com.vankrimpen.dustin;
/*
 * Task 4)
 * Implementation of a queue that holds integers 
 */
public class IntQueue {
	int[] intArray;
	final int front = 0; // Front of queue is always at 0. When an element is dequeued, all other elements are shifted over. 
	int rear, size, capacity; // Rear stores where the last element is stored in array. Size holds current size of queue. Capacity is the maximum possible queue size.  
	
	/*
	 * Constructor initializes array with specified capacity, and sets rear and size to 0. 
	 */
	public IntQueue(int capacity) {
		intArray = new int[capacity];
		size = 0;
		rear = 0;
		this.capacity = capacity;
	}
	// check if queue is at maximum capacity 
	public boolean isFull() {
		if(size == capacity) {
			return true;
		}
		else {
			return false;
		}
	}
	// check if queue contains no elements
	public boolean isEmpty() {
		if(size > 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//Add an element to the end of queue if queue is not full 
	public void enqueue(int x) {
		if (!isFull()) {
			intArray[rear] = x;
			rear += 1;
			size += 1;
		}
		else {
			System.out.println("Stack full. Unable to add to stack.");
		}
	}
	
	//If queue is not empty, remove and return the first element of queue and shift any other elements forward
	public int dequeue() {
		if (isEmpty()) {
			System.out.println("Cannot dequeue empty queue... returning 0.");
			return 0;
		}
		else {
			int x = intArray[front];
			for (int i =1;i<size;i++) {
				//shift all elements over
				intArray[i-1] = intArray[i];
			}
			rear -= 1;
			size -= 1;
			return x;
		}
	}
	
}
