/*
Description: This program decrypts a file that was encrypted using Ceasar cipher. 
Programmer: Apollo Schaefer
Course:     COSC 211, SU '23
Lab #:      7
Due date:   7-27-23
 */
import java.io.*;
import java.util.*;
public class Decryption {
	public static void main(String[] args) {
		//Declare variables
		Scanner kb = new Scanner(System.in);
		boolean loop = true;
		int key = 0;
		DataOutputStream output = null;
		DataInputStream input = null;
		//Loop to continue until successful decryption
		do {
			try {
				//Prompt/Scan for file names
				System.out.print("\nEnter an input (or encrypted) file name: ");
				input = new DataInputStream(new FileInputStream(kb.next()));
				System.out.print("Enter an ouput (or unecrypted) file name: ");
				output = new DataOutputStream(new FileOutputStream(kb.next()));
				//Prompt/Scan for key
				System.out.print("Enter the secret key: ");
				key = kb.nextInt();
				loop = false;
			}
			catch (FileNotFoundException ex) {
				System.out.println("Bad input file name, TRY AGAIN");
			}
			catch (InputMismatchException ex) {
				System.out.println("Invalid key - key must be an integer, TRY AGAIN");
				kb.nextLine();
			}

		} while(loop);
		//Decode
		try {
			while(true) {
				byte before = input.readByte();
				output.writeByte(before - key);
			}
		}
		catch(EOFException ex) { 
			kb.close();
			System.out.println("\nFile is decrypted successfully!");
		}
		catch(IOException ex) {
			System.out.println("IOException occured");
		}

	}
}
/*
*  Program Output:
Programmer: Apollo Schaefer
Course:     COSC 211, SU '23
Lab #:      7
Due date:   7-27-23

Enter an input (or encrypted) file name: lab7EE.txt
Bad input file name, TRY AGAIN

Enter an input (or encrypted) file name: lab7E.txt
Enter an ouput (or unecrypted) file name: lab7D.txt
Enter the secret key: A
Invalid key - key must be an integer, TRY AGAIN

Enter an input (or encrypted) file name: lab7E.txt
Enter an ouput (or unecrypted) file name: lab7D.txt
Enter the secret key: 5

File is decrypted successfully!

Encrypted file (lab7E.txt):
N%|nqq%jshw~uy%ymnx%knqj%zxnsl%Hfjxfw%hnumjwHfjxfw%hnumjw%nx%sfrji%fkyjw%Ozqnzx%Hfjxfw1%|mt%zxji%ny%ns%mnxuwn{fyj%htwwjxutsijshjOzqnzx%Hfjxfw%|fx%f%Wtrfs%ljsjwfq%|mt%uqf~ji%f%rfotw%wtqj%nsjxyfgqnxmnsl%ymj%Wtrfs%JrunwjYmnx%nx%f%y~uj%tk%xzgxynyzynts%hnumjwNs%xzgxynyzynts%hnumjw%f%hmfwfhyjw%kwtr%ymj%uqfnsyj}y%nxwjuqfhji%|nym%fstymjw%hmfwfhyjw3

Decrypted file (lab7D.txt): 
I will encrypt this file using Caesar cipher
Caesar cipher is named after Julius Caesar, who used it in his
private correspondence

Julius Caesar was a Roman general who played a major role in
establishing the Roman Empire

This is a type of substitution cipher
In substitution cipher a character from the plaintext is
replaced with another character.

*/

