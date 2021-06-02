package com.vankrimpen.dustin;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Task 7)
 * TestGui is a graphic interface that allows a user to test out stack and queue implementations, and palindrome checker 
 */
public class TestGUI { 
	CharStack charStack; //character stack
	IntStack intStack; // integer stack 
	IntQueue intQueue; // integer queue 
	// isCharStack is used for the stack demo to tell which type of stack the user is using (true = charStack, false = intStack) 
	boolean isCharStack;
	// queueInitialized is used for the queue demo to tell whether or not the user has created a queue to test (true yes, false no) 
	boolean queueInitialized;
	
	/*
	 * Constructor 	
	 */
	public TestGUI() {
		intStack = new IntStack();
		charStack = new CharStack();
		// tabbedPane holds the three tabs of the program: stack demo, palindrome checker, and queue demo.
		JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Stack Demo", makeStackPanel());
        tabbedPane.add("Palindrome Checker", makePalindromePanel());
        tabbedPane.add("Queue Demo", makeQueuePanel());
        // frame is the main display frame of the program 
		JFrame frame = new JFrame(); 
		frame.add(tabbedPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Stacks and Queues Demo");
		frame.pack();
		frame.setVisible(true);
		
	}
	
	/*
	 * Main method
	 * Creates a new instance of TestGUI
	 */
	public static void main(String[] args) {
		new TestGUI();
	}
	
	/*
	 * Creates and sets up everything for the palindrome checker panel. 
	 * When this panel is selected, the user can enter strings and check if they are palindromes  
	 */
	private JPanel makePalindromePanel() {
		JTextField palindromeInput = new JTextField(); // text field for user input 
		JLabel palindromeMessage = new JLabel("Enter your text and press the button to check if it is a palindrome."); // prompts the user, and shows user a message depending on input 
		// The arrays hold multiple possible messages for confirming/rejecting input as a palindrome 
		String[] confirmPalindrome = new String[] { "YES! It 's a palindrome", "WOW! That is indeed a palindrome!", "AMAZING! That's a palindrome!", "YAY! You entered a palindrome!" };
		String[] rejectPalindrome = new String[] {"BOO! That's not a palindrome!", "GROSS! That is not a palindrome", "NOOOOO! You didn't enter a palidrome!", 
				"Are you kidding? That's obviously not a palindrome!" };
		
		/*
		 * Button for user to submit their input for palindrome checking 
		 */
		JButton checkPalindrome = new JButton("Check for Palindrome");
		checkPalindrome.addActionListener(e -> {
			String input = palindromeInput.getText();
			// if the input is empty or has no letters then prompt user to enter something
			if (input.trim().isEmpty() || !input.chars().anyMatch(Character::isLetter)) {
				palindromeMessage.setText("Enter a word or a phrase first, then press the button!");
				palindromeInput.setText("");
			}
			else {
				Random random = new Random();
				if (Test.isPalindrome(input)) {
					// select and show a random confirmation message
					int r = random.nextInt(confirmPalindrome.length);
					palindromeMessage.setText(confirmPalindrome[r]);
					palindromeInput.setText("");
				}
				else {
					// select and show a random rejection message
					int r = random.nextInt(rejectPalindrome.length);
					palindromeMessage.setText(rejectPalindrome[r]);
					palindromeInput.setText("");
				}
			}
			
		});
		
		// setup and add elements to pPanel 
		JPanel pPanel = new JPanel(); 
		pPanel.setLayout(new BorderLayout());
		pPanel.add(BorderLayout.NORTH, palindromeMessage);
		pPanel.add(BorderLayout.CENTER, palindromeInput);
		pPanel.add(BorderLayout.SOUTH, checkPalindrome);
        pPanel.setBorder(BorderFactory.createEmptyBorder(100,50,200,50));
        // add pPanel to allConent and return allContent 
        JPanel allContent = new JPanel();
        allContent.setLayout(new BorderLayout());
        allContent.add(BorderLayout.NORTH, pPanel);
        return allContent;
	}
	
	/*
	 * Creates everything that is in the queue demo panel 
	 * When this panel is selected, the user can create an IntQueue and test all its methods  
	 */
	private JPanel makeQueuePanel() {
		queueInitialized = false; // false until user initializes a queue 
		JTextField queueInput = new JTextField(10); // user input text field	
        JTextArea queueConsole = new JTextArea(10,0); // displays what is happening to user 
        queueConsole.append( "*** Queue Demo ***\n");
        queueConsole.setEditable(false);
        JScrollPane queueScrollPane = new JScrollPane(queueConsole); // allows user to scroll in queueConsole 
 
        JLabel queueMessage = new JLabel("Enter the size of the queue in the text box below, then press the 'Initialize' button to create a new queue.");
      
        /*
         * dequeue button 
         * only visible if a queue has been initialized 
         */
        JButton dequeue = new JButton("Dequeue");
        dequeue.setVisible(false);
        dequeue.addActionListener(e -> {
        	if(!queueInitialized) {
        		queueConsole.append("No queue to dequeue from...\n");
        	}
        	else if (intQueue.isEmpty()) {
        		queueConsole.append("Queue is empty. Nothing to dequeue...\n");
        	}
        	else {
        		queueConsole.append(String.format("Dequeue: %d \n", intQueue.dequeue()));
        	}
        });
        
        /*
         * mainQueueButton 
         * Is an 'Initialize' button when the queue is not initialized.
         * Is a 'Enqueue' button when the queue is initialized 
         */
    	JButton mainQueueButton = new JButton("Initialize");
    	mainQueueButton.addActionListener(e -> {
    		if (Test.isInt(queueInput.getText())) {
    			int x = Integer.parseInt(queueInput.getText());
    			if (queueInitialized ) {
        			if (intQueue.isFull()) {
        				queueConsole.append("Queue is full! Please dequeue before enqueuing more.\n");
        				queueInput.setText("");
        			}
        			else {
        				intQueue.enqueue(x);
        				queueConsole.append(String.format("Added %d to queue.\n", x));
        				queueInput.setText("");
        			}
        		}
        		else {
        			intQueue = new IntQueue(x);
        			queueInitialized  = true;
        			dequeue.setVisible(true);
        			queueConsole.append(String.format("Initialized new queue with size of %d \n", x));
        			mainQueueButton.setText("Enqueue");
        			queueInput.setText("");
        			queueMessage.setText("Enter an integer below, then click 'Enqueue' to add it to the queue." );
        		}
			}
    		else {
    			queueConsole.append(String.format("Invalid input. Please enter an integer between %1$s and %2$s \n",  Integer.MIN_VALUE, Integer.MAX_VALUE));
    			queueInput.setText("");
    		}
    		
    	});
    	
    	/*
    	 * Button to clear contents of queueConsole 
    	 */
    	JButton clearQueueOutput = new JButton("Clear Output");
    	clearQueueOutput.addActionListener(e -> {
    		queueConsole.setText("");
    	});
    	
    	/* 
    	 * Resets the queue and changes initialized to false if it's not already 
    	 */
        JButton resetQueue = new JButton("Reset Queue");
        resetQueue.addActionListener(e -> {
        	if (queueInitialized) {
        		intQueue = new IntQueue(1);
        		queueInitialized = false;
        		mainQueueButton.setText("Initialize");
        		dequeue.setVisible(false);
        		queueConsole.append("Resetting queue. Please specify size of new queue below and click 'Initialize'.\n");
    			queueMessage.setText("Enter the size of the queue in the text box below, then press the 'Initialize' button to create a new queue.");
        	}
        	else {
        		queueConsole.append("There is no queue to reset...\n");
        	}
        });
        
        JButton reverseQueue = new JButton("Reverse order");
        reverseQueue.addActionListener(e -> {
        	if(queueInitialized && !intQueue.isEmpty()) {
        		Test.reverseQueue(intQueue);
        		queueConsole.append("Order Reversed.\n");
        	}
        	else {
        		queueConsole.append("Nothing to reverse...\n");
        	}
        });
        
        // panel 1 is the main panel which all other panels are added to 
        JPanel queuePanel1 = new JPanel();
        // panels 2 - 4 are sub-panels 
        JPanel queuePanel2 = new JPanel();
        JPanel queuePanel3 = new JPanel();
        JPanel queuePanel4 = new JPanel();

        // panel2 is the bottom panel 
        queuePanel2.add(queueInput);
        queuePanel2.add(mainQueueButton);
        queuePanel2.add(dequeue);
        // panel 3 is the top panel 
        queuePanel3.add(resetQueue);
        queuePanel3.add(reverseQueue);
        queuePanel3.add(clearQueueOutput);
        queuePanel4.setLayout(new BorderLayout());
        // panel 4 is the middle panel 
        queuePanel4.add(BorderLayout.NORTH, queueScrollPane);
        queuePanel4.add(BorderLayout.CENTER, queueMessage);
        // add sub panels to main panel 
        queuePanel1.setLayout(new BorderLayout());
        queuePanel1.add(BorderLayout.NORTH, queuePanel3);
        queuePanel1.add(BorderLayout.CENTER, queuePanel4);
        queuePanel1.add(BorderLayout.SOUTH, queuePanel2);
        queuePanel1.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        // add queuePanel1 to allConent panel and return allContent panel 
        JPanel allContent = new JPanel();
        allContent.setLayout(new BorderLayout());
        allContent.add(BorderLayout.NORTH, queuePanel1);
        return allContent;
	
	}
	
	/*
	 * Creates everything in the stack example pane.
	 * Initialized with values for char stack to start with.
	 * User can choose to switch between interacting with char stack or int stack.
	 */
	private JPanel makeStackPanel() {
		// boolean to store whether user is currently interfacing with char or int stack
        isCharStack = true; 
        
        // stackConsole shows the user what's happening to the stack
        JTextArea stackConsole = new JTextArea(10,0);  
        stackConsole.append( "*** Stack Demo *** \n");
        stackConsole.setEditable(false);
        
        JScrollPane stackScrollPane = new JScrollPane(stackConsole); // allows user to scroll up and down stackConsole
        JCheckBox stackCheckBox = new JCheckBox("Remove spaces", false); // stackCheckBox is used to toggle options. For intStack: split by digit. For charStack: remove spaces.
        JTextField stackInput = new JTextField(10); // text field for user input 
        // stackMessage displays basic instructions to the user 
        JLabel stackMessage = new JLabel("Enter a string below. Push it and it will be split up by characters and pushed to the stack.");
        
        /*
         * stackToggle Button 
         * used to toggle between int stack and char stack 
         */
        JButton stackToggle = new JButton("Switch to int stack");
    	stackToggle.addActionListener(e -> {
    		if (isCharStack) {
    			stackConsole.append("Switched to integer stack.\n");
				isCharStack = false;
				stackToggle.setText("Switch to char stack");
				stackMessage.setText("Enter an integer below. Push it and it will be added to the stack.");
				stackCheckBox.setText("Split integer by digit.");
				stackCheckBox.setSelected(false);

			}
			else {
				isCharStack = true;
    			stackConsole.append("Switched to character stack.\n");
				stackToggle.setText("Switch to int stack");
				stackMessage.setText("Enter a string below. Push it and it will be split up by characters and pushed to the stack.");
				stackCheckBox.setText("Remove Spaces");
				stackCheckBox.setSelected(false);
			}
    	});
    	
    	/*
    	 * clears contents of stackConsole 
    	 */
    	JButton stackClearOutput = new JButton("Clear Output");
    	stackClearOutput.addActionListener(e -> {
    		stackConsole.setText("");
    	});
        
    	/*
    	 * stackPush Button
    	 * pushes valid input to the selected stack
    	 */
    	JButton stackPush = new JButton("Push");
        stackPush.addActionListener(e -> {
        	if (isCharStack) { // when using charStack, input string is split up by characters, and each individual character is added to the stack
				String content = stackInput.getText();
				if(stackCheckBox.isSelected()) {
					content = content.replaceAll(" ", "");
				}
				ArrayList<Character> charList = (ArrayList<Character>) Test.stringToCharList(content);
				charStack.pushAll(charList);
				stackConsole.append("Character stack updated!\n");
			}
			else { // int stack 
				if (Test.isInt(stackInput.getText())) {
					int x = Integer.parseInt(stackInput.getText());
					if(stackCheckBox.isSelected()) { // if check box is selected, integers are split up by digit and individual digits are added to stack 
						LinkedList<Integer> digits = new LinkedList<Integer>();
						digits = Test.digitSplitter(x);
						IntStack digitStack = new IntStack();
						for (Integer digit : digits) {
							digitStack.push(digit);
						}
						while (!digitStack.isEmpty()) {
							int y = digitStack.peek();
							digitStack.pop();
							intStack.push(y);
						}
						
					}
					else {
						intStack.push(x);
					}
					
					stackConsole.append("Integer stack updated.\n");
				}
				else {
					stackConsole.append(String.format("Invalid input. Please enter an integer between %1$s and %2$s \n",  Integer.MIN_VALUE, Integer.MAX_VALUE));
				}
				
			}
        	
        	stackInput.setText("");
        });

        /*
         * stackPop Button 
         */
    	JButton stackPop = new JButton("Pop");
    	stackPop.addActionListener(e -> {
    		if (isCharStack) {
				if (charStack.isEmpty()) {
					stackConsole.append("Stack is empty...\n");
				}
				else {
					Character ch = (Character) charStack.peek();
					String str = "Popping: " + ch;
					str += "\n";
					stackConsole.append(str);
					charStack.pop();
				}
				
			}
    		else {
    			if (intStack.isEmpty()) {
					stackConsole.append("Stack is empty...\n");
				}
				else {
					String str = "Popping: " + intStack.peek();
					str += " \n";
					stackConsole.append(str);
	    			intStack.pop();
				}
				
    			
    		}
    	});
        
    	/*
    	 * stackPeek Button
    	 */
    	JButton stackPeek = new JButton("Peek");
    	stackPeek.addActionListener(e -> {
    		if (isCharStack) {
    			if (charStack.isEmpty()) {
    				stackConsole.append("Stack is empty...\n");
    			}
    			else {
    				Character ch = (Character) charStack.peek();
					String str = "Peek: " + ch;
					str += " \n";
					stackConsole.append(str);
    			}
    		}
    		else {
    			if (intStack.isEmpty()) {
    				stackConsole.append("Stack is empty...\n");
    			}
    			else {
    				Integer digit = (Integer) intStack.peek();
					String str = "Peek : " + digit;
					str += " \n";
					stackConsole.append(str);
    			}
    		}
    	});

    	/*
    	 * stackReset Button
    	 */
        JButton stackReset = new JButton("Reset Stack");
        stackReset.addActionListener(e -> {
        	if (isCharStack) {
        		charStack = new CharStack();
        		stackConsole.append("<Character stack reset!>\n");
        	}
        	else {
        		intStack = new IntStack();
        		stackConsole.append("<Integer stack reset!>\n");

        	}
        });

        // panel 1 is the main panel which all other panels are added to  
        JPanel stackPanel1 = new JPanel();
        // panels 2 - 4 are sub-panels 
        JPanel stackPanel2 = new JPanel();
        JPanel stackPanel3 = new JPanel();
        JPanel stackPanel4 = new JPanel();
        // panel 2 is the top panel
        stackPanel2.add(stackToggle);
        stackPanel2.add(stackReset);
        stackPanel2.add(stackClearOutput);
        // panel 3 is the bottom panel
        stackPanel3.add(stackInput);
        stackPanel3.add(stackPush);
        stackPanel3.add(stackPeek);
        stackPanel3.add(stackPop);
        // panel 4 is the center panel 
        stackPanel4.setLayout(new BorderLayout());
        stackPanel4.add(BorderLayout.NORTH, stackScrollPane);
        stackPanel4.add( BorderLayout.CENTER, stackMessage);
        stackPanel4.add( BorderLayout.SOUTH, stackCheckBox);
        // add all sub panels to panel 1 
        stackPanel1.setLayout(new BorderLayout());
        stackPanel1.add(BorderLayout.NORTH, stackPanel2);
        stackPanel1.add(BorderLayout.CENTER, stackPanel4);
        stackPanel1.add(BorderLayout.SOUTH, stackPanel3);
        stackPanel1.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
       // allContent holds panel1 and is returned 
        JPanel allContent = new JPanel();
        allContent.setLayout(new BorderLayout());
        allContent.add(BorderLayout.NORTH, stackPanel1);
        return allContent;
		
	}

}
