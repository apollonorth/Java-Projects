/*
Description: This program encrypts a file using Ceasar cipher. 
Programmer: Apollo Schaefer
Course:     COSC 211, SU '23
Lab #:      7
Due date:   7-27-23
 */
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Encryption {
	public static void main(String[] args) {
		//Create scanner object for kb input
		Scanner kb = new Scanner(System.in);
		boolean loop = true;
		int key = 0;
		DataInputStream input = null;
		DataOutputStream output = null;
		do {
			try {
				//Prompt/Scan file input and output file names
				System.out.print("\nEnter an input file name: ");
				String fileNameIn = kb.next();
				System.out.print("Enter an output file name: ");
				String fileNameOut = kb.next();
				//Create streams
				FileInputStream inputSt = new FileInputStream(fileNameIn);
				input = new DataInputStream(inputSt);
				FileOutputStream outputSt = new FileOutputStream(fileNameOut);
				output = new DataOutputStream(outputSt);
				//Key
				System.out.print("Enter the secret key: ");
				key = kb.nextInt();
				loop = false;
			}
			catch(FileNotFoundException ex) {
				System.out.println("Bad input file name, TRY AGAIN");
			}
			catch(InputMismatchException ex) {
				System.out.println("Invalid key - key must be an integer, TRY AGAIN");
				kb.nextLine();
			}
		} while(loop);
		//Encrypt file
		try {
			while(true) {
				byte before = input.readByte();
				output.writeByte(before + key);
			}
		}
		catch(EOFException ex) { 
			kb.close();
			System.out.println("\nFile is encrypted successfully!");
		}
		catch(IOException ex) {
			System.out.println("IOException occured");
		}
		
	}
}
/*
Program output: 
Programmer: Apollo Schaefer
Course:     COSC 211, SU '23
Lab #:      7
Due date:   7-27-23

Enter an input file name: lab77.txt
Enter an output file name: lab7E.txt
Bad input file name, TRY AGAIN

Enter an input file name: lab7.txt
Enter an output file name: lab7E.txt
Enter the secret key: 5.7
Invalid key - key must be an integer, TRY AGAIN

Enter an input file name: lab7.txt
Enter an output file name: lab7E.txt
Enter the secret key: 5

File is encrypted successfully!

Original file (lab7.txt):
I will encrypt this file using Caesar cipher
Caesar cipher is named after Julius Caesar, who used it in his
private correspondence

Julius Caesar was a Roman general who played a major role in
establishing the Roman Empire

This is a type of substitution cipher
In substitution cipher a character from the plaintext is
replaced with another character.

Encrypted file (lab7E.txt):
N%|nqq%jshw~uy%ymnx%knqj%zxnsl%Hfjxfw%hnumjwHfjxfw%hnumjw%nx%sfrji%fkyjw%Ozqnzx%Hfjxfw1%|mt%zxji%ny%ns%mnxuwn{fyj%htwwjxutsijshjOzqnzx%Hfjxfw%|fx%f%Wtrfs%ljsjwfq%|mt%uqf~ji%f%rfotw%wtqj%nsjxyfgqnxmnsl%ymj%Wtrfs%JrunwjYmnx%nx%f%y~uj%tk%xzgxynyzynts%hnumjwNs%xzgxynyzynts%hnumjw%f%hmfwfhyjw%kwtr%ymj%uqfnsyj}y%nxwjuqfhji%|nym%fstymjw%hmfwfhyjw3

 */

