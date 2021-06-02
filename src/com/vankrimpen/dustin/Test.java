package com.vankrimpen.dustin;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * Test class is a collection of methods that are used to run the TestGUI
 */
public class Test {
	/* Task 6)
	 * Splits a given integer by its digits and returns a LinkedList of digits 
	 */
    public static LinkedList<Integer> digitSplitter(int i) {
        LinkedList<Integer> digits = new LinkedList<>();
        if (i==0) {
        	digits.add(0);
        }
        else {
        	while (i>0) { //The the digits are taken from the remainders of dividing the given integer by ten. This is done continuously until the given integer is <= 0  
                digits.add(i%10);
                i/=10;
            }
        }
        
        return digits;
    }
    
    /*
	 * isInt checks a string and returns true if it is an integer/false if it is not an integer.
	 * An attempt is made to parse the string to int. If this fails, the exception is handled and false is returned. 
	 */
	public static boolean isInt(String input) throws NumberFormatException{
		boolean isInt = false;
		try {
			Integer.parseInt(input);
			isInt = true;
		}
		catch (Exception e) {
			isInt = false;
		}
		
		return isInt;
	}
	
	/*
	 * Task 3)
	 * Uses CharStack to check a string and returns true if palindrome, false if not palindrome
	 */
	public static boolean isPalindrome(String input) {
		input = input.replaceAll("[^a-zA-Z]", "").toLowerCase();// Regex used to only check letters
		CharStack chStack = new CharStack(input);//input is put into a charStack,
		String reverseInput =""; // To store reversal of input 
		while(!chStack.isEmpty()) {
			reverseInput += chStack.peek(); // pull chars off the top of stack and add them to reverseInput 
			chStack.pop();
		}
		// check if reverseInput is the same as input and return true if so
		if (reverseInput.equals(input)) { 
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * For Task 5)
	 * Reverse queue uses only a stack (s), a queue (q), and a single element variable (x) to reverse the order of q 
	 */
	public static void reverseQueue(IntQueue q) {
		IntStack s = new IntStack();
		int x;
		// dequeue from queue (q) to stack (s)
		while (!q.isEmpty()) {
			x = q.dequeue();
			s.push(x);
		}
		// peek/pop from stack (s) back to queue (q) 
		while(!s.isEmpty()) {
			q.enqueue((int) s.peek());
			s.pop();
		}
		
	}
	
	/*
	 * stringToChar() takes a string, splits it up by character, and adds each character to an array list which is returned. 
	 */
	public static ArrayList<Character> stringToCharList(String input){
		ArrayList<Character> charList = new ArrayList<Character>();
		for (char ch : input.toCharArray()) {
			charList.add(ch);
		}
		return charList;
		}
	
}
