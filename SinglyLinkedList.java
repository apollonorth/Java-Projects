/**
 * Description: Define a class that represents a list of integers in the form of a linked list. My own implementation of useful methods for working with lists, 
 * including a recursive printBackwards method.
 *
 * Programmer: Apollo Schaefer
 * Course: COSC 211, SU '23
 * Lab:    9
 * Date: 8-3-23
 */
public class SinglyLinkedList {
	// data members
	private Node head;
	
	// constructor
	public SinglyLinkedList() {
		head = null;
	}
	
	// add a node at the beginning of the list
	public void addFirst (int value) {
		head = new Node (value,head);
	}
	

	// remove the first node from the list 
	// this method assumes the node class is not an inner class
	public boolean removeFirst () {
		// is list empty?
		if (head == null)
			return false;
		head = head.getNext();
		return true;
		
	}		
		
	// add to the end of list
	public void addLast (int value) {
		
		// is list empty
		if (head == null)
			addFirst(value);
		else {
			Node current = head;
			while (current.getNext() != null) 
				current = current.getNext();
			current.setNext( new Node(value));
		}
		
	}	
	
	// output the list
	public void print () {
		Node current = head;
		while (current != null) {
			// grab the value and print it
			System.out.print(current.getData() + "  ");
			
			// advance to the next node
			current = current.getNext();
		}
		System.out.println();
	}
		

	// search the list for a target value. Return true
	// if target is in the list, and false otherwise
	public boolean find (int target) {
		Node current = head;
		while (current != null) {
			if (current.getData() == target)
				return true;
			current = current.getNext();
		}
		return false;
	}
	
	//Return num of elements in LinkedList
	public int size() {
		int count = 0;
		if (head == null)
			return 0;
		Node current = head;
		while(current.getNext() != null) {
			current = current.getNext();
			count++;
		}
		return count + 1;
	}
	
	//Print the list returned by toString
	public String toString() {
		Node current = head;
		if(current == null)
			return "";
		String list = "";
		list += "[";
		list += current.getData();
		while (current.getNext() != null) {
			current = current.getNext();
			list += " , " + current.getData();
		}
		list += "]";
		return list;
	}
	
	//Remove last element
	public boolean removeLast() {
		if(head == null) {
			return false;
		} 
		else if(head.getNext() == null) {
			head = null;
			return true;
		}
		Node current = head;
		while(current.getNext().getNext() != null) {
			current = current.getNext();
		}
		current.setNext(null);
		return true;
	}
	
	//Increment all elements by n
	public void increment(int n) {
		Node current = head;
		while(current.getNext() != null) {
			current.setData(current.getData() + n);
			current = current.getNext();
		}
		current.setData(current.getData() + n);
	}
	
	//Print backward helper
	public void printBackward() {
		printBackward(head);
	}
	//Backward recursive
	public void printBackward(Node current) {
		if(current == null) {
			return;
		}
		else {
			printBackward(current.getNext());
			System.out.print(current.getData() + " ");
		}
	}
		
}


