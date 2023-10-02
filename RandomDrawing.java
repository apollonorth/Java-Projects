/*
 * Description: Defines a class that implements cloneable interface and stores generic values into an ArrayList. 
 * Has a method to randomly draw objects from the list.
 * Requires: Driver class with main method.
 * Programmer: Apollo Schaefer
 * Course: COSC 211, SU '23
 * Lab:    10
 * Date: 8-10-23
 */

import java.util.ArrayList;

public class RandomDrawing <T> implements Cloneable {
	//Private list for holding values
	private ArrayList<T> box;
	
	//No-arg constructor
	public RandomDrawing () {
		box = new ArrayList<T>();
	}
	
	//Add value to list
	public void add(T value) {
		box.add(value);
	}
	
	//Empty?
	public boolean isEmpty() {
		return box.isEmpty();
	}
	
	//ToString
	@Override
	public String toString() {
		String list = "";
		for(int i = 0; i < box.size(); i++)
			list += box.get(i) + " ";
		
		return list;
	}
	
	//Clone - deep copy
	@Override
	public Object clone(){
		try {
			//Shallow Copy
			RandomDrawing<T> drawingClone = (RandomDrawing<T>) super.clone();
			drawingClone.box = ( ArrayList<T>)this.box.clone();
			return drawingClone;
		}
		//Casting potential exception
		catch(ClassCastException ex) {
			System.out.println("Class Cast Exception.");
			return null;
		}
		//super.clone potential exception
		catch (CloneNotSupportedException ex) {
			System.out.println("Clone Not Supported Exception.");
			return null;
		}
		
	}
	
	//Random drawing using Math.random()
	public T drawItem() {
		if (box.isEmpty()) {
			return null;
		}
		else {
			int index = (int) (Math.random() * box.size());
			T returnObj = box.get(index);
			box.remove(index);
			return returnObj;
		}
	}
	
}
