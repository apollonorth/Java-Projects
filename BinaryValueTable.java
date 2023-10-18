/*
Programmer: Apollo Schaefer
Project Title: Signed Integer Binary Table
Submission Date: October 18, 2023
Description: This program asks for a length of binary digits and outputs a table displaying every possible binary combination for that number of bits,
along with their values in signed magnitude, 1s complement, 2s complement, and Excess Notation


 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class BinaryValueTable {
	public static void main(String[] args) {
		//Call the method to get the length, or number of bits, from the user
		int selection = getSelection();
		int numBits = getNumBits();
		//Call the method to print the heading to the console
		tableHeading(selection);
		//Call method to print table
		printTable(selection, numBits);
	}
	
	public static int getSelection() {
		//Give options for the desired output notation, return the selection
		Scanner kb1 = new Scanner(System.in);
		System.out.println("Select a number for desired output notation\n-------------------------------------------\n1: Signed Magnitude\n2: 1s Complement\n3: 2s Complement\n4: Excess Notation\n5: All Notations");
		int selection = kb1.nextInt();
		return selection;
	}
	
	public static int getNumBits() {
		//Declare  variable, create Scanner object for keyboard input
		int numBits = 0;
		boolean loop = true;
		Scanner kb = new Scanner(System.in);
		//Loop until acceptable input is given (integer > 0)
		while(loop || numBits < 1) {
			System.out.print("Length: ");
			try {
				numBits = kb.nextInt();
				if(numBits < 1) 
					System.out.println("The integer must be greater than 0, try again.");
				loop = false; // exit loop given numBits > 0
			}
			catch (InputMismatchException ex) {
				System.out.println("Input must be an integer greater than 0, try again.");
				 //continue loop if invalid input, consume input to prevent infinite loop
				loop = true;
				kb.next();
			}
		}
		//Close input stream and return number of bits/length
		kb.close();
		return numBits;
	}
	
	public static void tableHeading(int selection) {
		//Store each header as value in String array
		String [] heading = {"Signed Magnitude", "1s Complement", "2s Complement", "Excess Notation"};
		System.out.println();
		//Print correct header depending on desired output notation
		System.out.print("\t\tBinary");
		switch (selection) {
			case 1: System.out.printf("%30s", heading[0]);
					System.out.println("\n\t\t------------------------------------");
					break;
			
			case 2:	System.out.printf("%28s", heading[1]);
					System.out.println("\n\t\t----------------------------------");
					break;
			
			case 3:	System.out.printf("%28s", heading[2]);
					System.out.println("\n\t\t----------------------------------");
					break;
			
			case 4:	System.out.printf("%30s", heading[3]);
					System.out.println("\n\t\t------------------------------------");
					break;
		
			case 5:	for(String elm: heading) {System.out.printf("%30s", elm);}
					System.out.println("\n\t\t------------------------------------------------------------------------------------------------------------------------------");
					break;
		}
	}
	
	public static void printTable(int selection, int numBits) {
		//Find number of rows (2^n)
		int numRows =(int)(Math.pow(2.0, numBits));
		// Run a loop that many times to print each row through a method call chain
		for(int i = 0; i < numRows; i++) {
			printRow(i, numBits, selection);
		}
	}
	
	public static void printRow(int rowNum, int numBits, int selection) {
		//Call the appropriate method for each column of the row
		//printBinary prints the appropriate representation and also returns the binary digits to be used for notation values
		ArrayList<String> binary = printBinary(rowNum, numBits);
		switch (selection) {
			case 1: 
				printSigned(binary, numBits);
				System.out.println("\n\t\t------------------------------------");
				break;
			case 2:
				String onesValue = printOnes(binary, numBits, selection);
				System.out.println("\n\t\t----------------------------------");
				break;
			case 3:
				onesValue = printOnes(binary, numBits, selection);
				printTwos(onesValue, binary);
				System.out.println("\n\t\t----------------------------------");
				break;
			case 4:
				printExcess(binary, numBits);
				System.out.println("\n\t\t------------------------------------");
				break;
			case 5:
				printSigned(binary, numBits);
				onesValue = printOnes(binary, numBits, selection);
				printTwos(onesValue, binary);
				printExcess(binary, numBits);
				//Print break line between next row
				System.out.println("\n\t\t------------------------------------------------------------------------------------------------------------------------------");
		}
		
	}
	
	public static ArrayList<String> printBinary(int rowNum, int numBits) {
		//Create String ArrayList to hold each bit value, ensuring desired length is returned
		ArrayList<String> binaryDigits = new ArrayList<>();
		//Set all bits to zero
		for(int i = 0; i < numBits; i++)
			binaryDigits.add("0");
		//If first row, binary String array should remain all zeros
		if(rowNum == 0) {}
		//Else, add (1*rowNum) to the digits using a for loop and method call to addOne
		else {
			for(int i = 0; i < rowNum; i++) {
				binaryDigits = addOne(binaryDigits, numBits);
			}
		}
		//print the ArrayList as binary
		System.out.print("\t\t");
		StringBuilder binaryStr = new StringBuilder();
		for(String elm: binaryDigits)
			binaryStr.append(elm);
		System.out.printf("%-27s", binaryStr);
		return binaryDigits;
	}
	
	public static ArrayList<String> addOne(ArrayList<String> digits, int numBits) {
		//Index variable to track which index was changed and avoid looping more than necessary
		int indexChanged = -1;
		//Check each digit, LSB to MSB, for a zero. Set that zero to one
		for(int i = numBits - 1; i >= 0; i--) {
			if (digits.get(i).equals("0")) {
				digits.set(i, "1");
				indexChanged = i;
				break;
			}
		}
		//Check for ones that need to be changed to zeros
		for(int i = numBits - 1; i > indexChanged; i--) {
			if(digits.get(i).equals("1")) {
				digits.set(i, "0");
			}
		}
		return digits;
	}
	
	public static void printSigned(ArrayList<String> binary, int numBits) {
		//Call a method to find the sign of the value
		String sign = getSign(binary);
		int sum = 0;
		int bitPlace = 0;
		for(int i = numBits - 1; i > 0; i--, bitPlace++) {
			sum +=  Integer.valueOf(binary.get(i)) * (int) (Math.pow(2,  bitPlace));
		}
		//Print the signed magnitude value
		String toPrint = sign + sum;
		System.out.printf("%-32s", toPrint);
	}

	public static String printOnes(ArrayList<String> binary, int numBits, int selection) {
		 int value = 0;
		 //Call method to get sign
		 String sign = getSign(binary);
		
		 //If value is positive, check each digit besides MSB for a 1
		 if(binary.get(0).equals("0")) {
			 for(int i = 0; i < numBits - 1; i++) {
				 //If the digit is one, add 2 to the power of its index to value variable
				 if(binary.get(numBits - 1 - i).equals("1")) {
					 value += Math.pow(2, i);
				 }
			 }
		 }
		 //If its negative, each 1 value must be subtracted from 2^(numBits - 1) - 1, as the first bit is just for the sign 
		 else {
			 value = (int) Math.pow(2, (numBits - 1)) - 1;
			 for(int i = 0; i < numBits - 1; i++) {
				 if(binary.get(numBits - 1 - i).equals("1")) {
					 value -= Math.pow(2, i);
				 }
			 }
		 }
		 String toPrint = sign + value;
		 if(selection == 5 || selection == 2)
			 System.out.printf("%-30s", toPrint);
			 
		 //return value to be used by printTwos method
		return value + "";
	}
	
	public static String getSign(ArrayList<String> binary) {
		//Check first digit and print the correct sign
		String sign;
		//If first digit is 0, it is positive, otherwise negative
		if(binary.get(0).equals("0")) {
			sign = "+";
		}
		else {
			sign = "-";
		}
		//Return the sign to decide the operation of the printTwos method
		return sign;
	}
	
	public static void printTwos(String onesValue, ArrayList<String> binary) {
		String sign = getSign(binary);
		if(sign.equals("+")) {
			System.out.printf("%-32s", (sign + onesValue));
		}
		else {
			System.out.printf("%-32s", (sign + (Integer.valueOf(onesValue) + 1)));
		}
	}
	
	public static void printExcess(ArrayList<String> binary, int numBits) {
		int sum = 0;
		int bitPlace = 0;
		for(int i = numBits - 1; i >= 0; i--, bitPlace++) {
			sum +=  Integer.valueOf(binary.get(i)) * (int) (Math.pow(2,  bitPlace));
		}
		
		int excess;
		//Excess equals sum - bias
		excess = sum - ((int)(Math.pow(2, (numBits - 1))));
	
		if (excess < 0)
			System.out.printf("%-30s", excess);
		else if (excess > 0) {
			String plusExcess = "+" + excess;
			System.out.printf("%-30s", plusExcess);
		}
		else
			System.out.printf(" %-30s", excess);
	}
}
